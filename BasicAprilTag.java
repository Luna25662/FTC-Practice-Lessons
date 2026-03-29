package org.firstinspires.ftc.teamcode;

//Before using the webcam, you need
//to setup the hardware so you can easily call
// it and implement it into your autonomous or auto.
// Think of it like preparing the table
// before placing the food. It's important
//so make sure to do this first before
// using the webcam.
//ALL imports you need
import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

public class BasicAprilTag {
    //An apriltagproccesor is what does the logic
    // to undertsand what an apriltag is.
    private AprilTagProcessor aprilTagProcessor;
    //The vision portal allows us to open up
    //live feeds so we can see what is going
    // on through the webcams
    private VisionPortal visionportal;

    //This list is a detection list that will give us
    //the info of the apriltag like the distance,
    //id_number, and other specifics.
    private List<AprilTagDetection> detectedTags = new ArrayList<>();

    private Telemetry telemetry;

    public void init(HardwareMap hwMap,Telemetry telemetry){
        this.telemetry = telemetry;

        //Initializing processor
        aprilTagProcessor = new AprilTagProcessor.Builder()
                //Think of the builder method as a way
                // to build you processor with traits
                // you want it to have.
                //Here you show the ID
                .setDrawTagID(true)
                //You draw a cube around the
                //Apriltag.
                .setDrawCubeProjection(true)
                //Here you draw the axes of the distance
                // towards the apriltag.
                .setDrawAxes(true)
                //Here we choose the units to show the projections.
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .build();

        //Here we build the vision portal with
        // the details of our camera.
        VisionPortal.Builder builder = new VisionPortal.Builder();
        //We call the camera from our driver hub
                builder.setCamera(hwMap.get(WebcamName.class, "webcam"));
                //We set the resolution of your camera here
                builder.setCameraResolution(new Size(640, 480));
                //Here we use the proccesor that we made
                //and connect it to our vision portal.
                builder.addProcessor(aprilTagProcessor);
                //Then we wrap everything up into the visionportal
                visionportal = builder.build();
    }
    //This updates to check if it has detected
    //any apriltags.
public void update(){
        detectedTags = aprilTagProcessor.getDetections();

    }

    //This return all tags detected by camera.
    public List<AprilTagDetection> getDetectedTags(){
        return detectedTags;
    }

    //This telemetry will display your distance and
    // other details with specifics.
    public void displayDetectionTelemetry(AprilTagDetection detectedID){
        if(detectedID == null) {return;}
        if (detectedID.metadata != null) {
            telemetry.addLine(String.format("\n==== (ID %d) %s", detectedID.id, detectedID.metadata.name));
            telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detectedID.ftcPose.x, detectedID.ftcPose.y, detectedID.ftcPose.z));
            telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detectedID.ftcPose.pitch, detectedID.ftcPose.roll, detectedID.ftcPose.yaw));
            telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detectedID.ftcPose.range, detectedID.ftcPose.bearing, detectedID.ftcPose.elevation));
        } else {
            telemetry.addLine(String.format("\n==== (ID %d) Unknown", detectedID.id));
            telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detectedID.center.x, detectedID.center.y));
        }

    }

  public Double getBearingSpecificId(int id){
      //This for loop goes through every
      //tag until it finds the tag
      //of your choosing.
      for(AprilTagDetection detection : detectedTags){
          if(detection.id == id){
              double bearing = detection.ftcPose.bearing;
              return bearing;
          }
      }
      return null;

  }

    //This return a specific tag ID of your choosing
    public AprilTagDetection getTafBySpecificID(int id){
        //This for loop goes through every
        //tag until it finds the tag
        //of your choosing.
        for(AprilTagDetection detection : detectedTags){
            if(detection.id == id){
                return detection;
            }
        }
        return null;
    }



    //This stops the vision portal when not used.
    public void stop(){
        if(visionportal != null){
            visionportal.close();
        }
    }
}
