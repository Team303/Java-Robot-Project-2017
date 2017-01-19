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
	private double centerX = 0.0;
	private double centerY = 0.0;
	
	Thread rawThread;
	AxisCamera rawCamera;
	CvSink rawCvSink;
	CvSource rawOutputStream;
	Mat rawMat;
	
	public Camera() {
		//enableRawCam(); //outputs a raw feed to the dashboard
		enableVisionThread(); //outputs a processed feed to the dashboard (overlays the found boiler tape)
	}
	
	public void enableVisionThread() {
		pipeline = new BoilerPipeline();
		AxisCamera camera = CameraServer.getInstance().addAxisCamera("10.3.3.31");
		camera.setResolution(160, 120);
		
		CvSink cvSink = CameraServer.getInstance().getVideo(); //capture mats from camera
		CvSource outputStream = CameraServer.getInstance().putVideo("Processed Stream", 160, 120); //send steam to CameraServer
		Mat mat = new Mat(); //define mat in order to reuse it
		
		visionThread = new Thread(() -> {
			
			while(!Thread.interrupted()) { //this should only be false when shutting down
				
				if(cvSink.grabFrame(mat)==0) { //fill mat with image from camera TODO exception handling (there is an error if it returns 0)
					outputStream.notifyError(cvSink.getError()); //send an error instead of the mat
					continue; //skip to the next iteration of the thread
				}
				
				pipeline.process(mat); //process the mat (this does not change the mat, and has an internal output to pipeline)
				
				if(!pipeline.filterContoursOutput().isEmpty()) {
					Rect rect = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0)); //get the first MatOfPoint (contour), calculate bounding rectangle
					
					//rect.x is the left edge as far as I can tell
					//rect.y is the top edge as far as I can tell
					centerX = rect.x + (rect.width/2); //returns the center of the bounding rectangle
					centerY = rect.y + (rect.height/2); //returns the center of the bounding rectangle

					//scalar(int, int, int) is in BGR color space as far as I can tell
					//the points are the two corners of the rectangle as far as I can tell
					Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 5); //draw rectangle of the detected object onto the image
					
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

	public void enableRawCam() {
		rawThread = new Thread(() -> {
			rawCamera = CameraServer.getInstance().addAxisCamera("10.3.3.31");
			rawCamera.setResolution(160, 120);
			rawCvSink = CameraServer.getInstance().getVideo();
			rawOutputStream = CameraServer.getInstance().putVideo("Rectangle", 160, 120);
			rawMat = new Mat();
			
			while(!Thread.interrupted()) {
				String sdOutput = runRawCam();
				SmartDashboard.putString("vision thread output", sdOutput);
			}
			
		});
		rawThread.setDaemon(true);
		rawThread.start();
	}
	
	public String runRawCam() {
		if(rawCvSink.grabFrame(rawMat)==0) {
			rawOutputStream.notifyError(rawCvSink.getError());
			return "errored";
		} else {
			rawOutputStream.putFrame(rawMat);
			return "did not error";
		}
		
	}	
}
