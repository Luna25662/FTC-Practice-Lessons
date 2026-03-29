//AUTO_ALIGN TELEOP(APRILTAGS)

package org.firstinspires.ftc.teamcode;

/*Welcome to the beginning of
 complex uses for autonomous 
 and TeleOp with webcams.
 If you are not familiar with april-tags, webcams,
 or anything beginner level, please go back to past lessons.
 This looks simple, but comprehension is critical for you to truly
 understand what is going on.
 Also, don't just copy and paste the code...
 only do it if you actually understand because
 trust me that you will get confused
 */
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@TeleOp
public class SimpleTagAlign extends OpMode {

    //Create motors
    DcMotor upperLeft, upperRight, lowerLeft, lowerRight;

    //Set up a boolean to
    //check when you are and
    //not tracking.
    boolean tracking = false;

    //Call from our webcam library
    BasicAprilTag aprilTagCam = new BasicAprilTag();

    public void init(){
        //initialize all hardware
        aprilTagCam.init(hardwareMap,telemetry);
        upperLeft = hardwareMap.dcMotor.get("upperLeft");
        upperRight = hardwareMap.dcMotor.get("upperRight");
        lowerLeft = hardwareMap.dcMotor.get("lowerLeft");
        lowerRight = hardwareMap.dcMotor.get("lowerRight");

        upperLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
//This loop will iterate through code again and again when pressing start.
    public void loop() {

        //updates apriltag info
        //or just frames.
        aprilTagCam.update();

        //Turning on and off tracking
        if (gamepad1.a) {
            tracking = true;
        }
        if (gamepad1.b) {
            tracking = false;
        }
        //Call a specific tag to track
        AprilTagDetection id20 = aprilTagCam.getTafBySpecificID(20);
        aprilTagCam.displayDetectionTelemetry(id20);
        //Bearing is the angle in which
        //the camera is away from the center
        //of the apriltag.
        Double bearing = aprilTagCam.getBearingSpecificId(20);

//checks if tracking is true, bearing
// is not null to run the chunk of code.
        if (tracking && bearing != null) {
//checks if bearing is
// greater than 5 degrees

            if (Math.abs(bearing) >5) {


//Turn power that can
// be adjusted for speed.
                double turnPower = bearing * 0.02;
//makes sure turn power is just right
                if (turnPower > 0.5){ turnPower = 0.5;}
                if (turnPower < -0.5) {turnPower = -0.5;}

                upperLeft.setPower
                        (turnPower);
                upperRight.setPower
                        (-turnPower);
                lowerLeft.setPower
                        (turnPower);
                lowerRight.setPower
                        (-turnPower);

            }
            else{
//Runs normally, but
// tracking is still on.
                double forward = 0.8 * gamepad1.left_stick_y;
                double strafe = 0.8 * gamepad1.left_stick_x;
                double turn = 0.8 * gamepad1.right_stick_x;

                upperLeft.setPower
                        (forward - strafe - turn);
                upperRight.setPower
                        (forward + strafe + turn);
                lowerLeft.setPower
                        (forward + strafe - turn);
                lowerRight.setPower
                        (forward - strafe + turn);


            }


        }
        //This runs if tracking is false
        else{

            double forward = 0.8 * gamepad1.left_stick_y;
            double strafe = 0.8 * gamepad1.left_stick_x;
            double turn = 0.8 * gamepad1.right_stick_x;

            upperLeft.setPower
                    (forward - strafe - turn);
            upperRight.setPower
                    (forward + strafe + turn);
            lowerLeft.setPower
                    (forward + strafe - turn);
            lowerRight.setPower
                    (forward - strafe + turn);


        }

    }

}
