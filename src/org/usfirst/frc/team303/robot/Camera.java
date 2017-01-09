package org.usfirst.frc.team303.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Camera {
	private Thread visionThread;
	private BoilerPipeline pipeline;
	private final int IMG_WIDTH = 640;
	private final int IMG_HEIGHT = 480;
	private double centerX = 0.0;
	private double centerY = 0.0;
	
	public Camera() {
		//enableRawThread(); //outputs a raw feed to the dashboard
		enableVisionThread(); //outputs a processed feed to the dashboard (overlays the found boiler tape)
	}
	
	public void enableVisionThread() {
		pipeline = new BoilerPipeline();
		AxisCamera camera = CameraServer.getInstance().addAxisCamera("axis-camera.local");
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		
		CvSink cvSink = CameraServer.getInstance().getVideo(); //capture mats from camera
		CvSource outputStream = CameraServer.getInstance().putVideo("Processed Stream", IMG_WIDTH, IMG_HEIGHT); //send steam to CameraServer
		Mat mat = new Mat(); //define mat in order to reuse it
		
		visionThread = new Thread(() -> {
			
			while(!Thread.interrupted()) { //this should only be false when shutting down
				cvSink.grabFrame(mat); //fill mat with image from camera TODO exception handling (there is an error if it returns 0)
				pipeline.process(mat); //process the mat (this does not change the mat, and has an internal output to pipeline)
				
				if(!pipeline.filterContoursOutput().isEmpty()) {
					Rect rect = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0)); //get the first MatOfPoint, calculate up-right bounding rectangle
					
					centerX = rect.x + (rect.width/2);
					centerY = rect.y + (rect.height/2);

					Imgproc.rectangle(mat, new Point(centerX - 20, centerY - 20), new Point(centerX + 20, centerY + 20), new Scalar(255, 255, 255), 5); //draw rectangle of the detected tape onto the image
					
					SmartDashboard.putString("Vision State", "Executed overlay!");
				}
				else {
					SmartDashboard.putString("Vision State", "Did not find goal");
				}
				
				outputStream.putFrame(mat); //give stream (and CameraServer) a new frame
			}
			
		});	
		visionThread.setDaemon(true);
		visionThread.start();
	}
	
	public double getCenterY() {
		return centerY;
	}

	public double getCenterX() {
		return centerX;
	}

	public void enableRawThread() {
		AxisCamera camera = CameraServer.getInstance().addAxisCamera("axis-camera.local");
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		CameraServer.getInstance().startAutomaticCapture(camera);
	}
	
}
