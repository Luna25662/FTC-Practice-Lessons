package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@Autonomous
public class AutoAprilTag extends OpMode {
    ElapsedTime timer;

    //We call the class that we made to implement it into
    //Our autonomous or even TeleOp if you want to.
    BasicAprilTag apriltagCam = new BasicAprilTag();
    DcMotor upperLeft, upperRight, lowerLeft, lowerRight;


    @Override
    public void init() {
        //Initialize hardware
        apriltagCam.init(hardwareMap, telemetry);
        upperLeft = hardwareMap.dcMotor.get("upperLeft");
        upperRight = hardwareMap.dcMotor.get("upperRight");
        lowerLeft = hardwareMap.dcMotor.get("lowerLeft");
        lowerRight = hardwareMap.dcMotor.get("lowerRight");
        //Set left wheels reverse
        upperLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        //We call the update method that we made
      apriltagCam.update();
      //We set the Ids we want to detect
        AprilTagDetection id15 = apriltagCam.getTafBySpecificID(15);
        AprilTagDetection id20 = apriltagCam.getTafBySpecificID(20);
        AprilTagDetection id14 = apriltagCam.getTafBySpecificID(14);
      apriltagCam.displayDetectionTelemetry(id15);
        apriltagCam.displayDetectionTelemetry(id20);
        apriltagCam.displayDetectionTelemetry(id14);


        //Set steps for the robot
        //to move and reach and detect
        //an apriltag.
        timer.reset();
        while(timer.seconds() >= 5){
            drive(0.8,0,500,0);
        }
        timer.reset();
        while(timer.seconds() >= 5 && timer.seconds() <=2){
            drive(0.8,0,500,0);
        }




        //One of these if statements will execute depending
        //on the apriltag detected.
        if(id15 != null){
            timer.reset();
            while(timer.seconds() >=3){
                drive(0.8,480,0,0);
            }

        }
        if(id14 != null){
            timer.reset();
            while(timer.seconds() >=3){
                drive(0.8,0,0,500);
            }

        }
        if(id20 != null){
            timer.reset();
            while(timer.seconds() >=7){
                drive(0.8,0,0,-500);
            }
        }


    }

    //This is an encoder based driving setup that we can easily manipulate
    // to move our robot one way.
    //An encoder counts based on rotations in the motor
    //We call this unit of counting, TICKS

    public void drive(double power, int forward, int strafe, int turn) {
        //Here we make sure to reset the encoders in case
        // they weren't by the motor itself.
        lowerLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        upperLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lowerRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        upperRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



//Here we set the wheel movement of the full robot. This is almost the exact
// same to what we did with our TeleOp to make our robot move forward, strafe, and turn.
        //We use setTarget position for encoders.
        lowerRight.setTargetPosition(forward - strafe + turn);
        upperRight.setTargetPosition(forward + strafe + turn);
        lowerLeft.setTargetPosition(forward + strafe - turn);
        upperLeft.setTargetPosition(forward - strafe - turn);



//This runs the motor to turn at that encoder movement.
        lowerLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        upperLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lowerRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        upperRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);



//This sets the power of the motor based on the user-input
        lowerLeft.setPower(power);
        upperLeft.setPower(power);
        lowerRight.setPower(power);
        upperRight.setPower(power);

//IDK what this is ngl
        while (lowerLeft.isBusy() && upperLeft.isBusy() && upperRight.isBusy() && lowerRight.isBusy()) {
        }

    }

}
