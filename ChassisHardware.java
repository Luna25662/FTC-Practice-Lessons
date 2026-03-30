package org.firstinspires.ftc.teamcode.Hardware;
//This isn't code you will use as your main,
// but you will just use it to use methods
// to simplify your other pieces of code.
//This means you will not have to type everything
//like the initialization for the chassis and
//even the drive method that you might be familiar with.

//This is just to save time and keep your
// code nice and neat. Not to make your robot
//better in any way, but it's always good to
// simplify things for you.
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ChassisHardware  {
    //name motors, and make sure they are
    // public, so you can access them for another class.
    public DcMotor upperLeft, upperRight, lowerLeft, lowerRight;


    //Here you initialize the hardware like always
    public void init(HardwareMap hwMap){
        upperLeft = hwMap.dcMotor.get("upperLeft");
        upperRight = hwMap.dcMotor.get("upperRight");
        lowerLeft = hwMap.dcMotor.get("lowerLeft");
        lowerRight = hwMap.dcMotor.get("lowerRight");

        //Makes ure to reverse the left side like always
lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);
upperLeft.setDirection(DcMotorSimple.Direction.REVERSE);

//Set them automatically to zero to avoid any troubles
        upperLeft.setPower(0);
        upperRight.setPower(0);
        lowerLeft.setPower(0);
        lowerRight.setPower(0);
    }

    //This is optional, but you can call
    // this method to not have to type it
    // out in every single class.
    //you can access it easily without having
    // to go through all the trouble.
    public void drive(double power, int forward, int strafe, int turn){
        lowerLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lowerRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        upperLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        upperRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lowerRight.setTargetPosition(forward - strafe + turn);
        upperRight.setTargetPosition(forward + strafe + turn);
        lowerLeft.setTargetPosition(forward + strafe - turn);
        upperLeft.setTargetPosition(forward - strafe - turn);

        lowerLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lowerRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        upperLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        upperRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        upperRight.setPower(power);
        lowerRight.setPower(power);
        upperLeft.setPower(power);
        lowerLeft.setPower(power);


    }




    //You can simplify your code by just
    public void teleOpControl(){
        double y_axis = .8* gamepad1.left_stick_y;
        double x_axis = .8*gamepad1.left_stick_x;
        double orientation = .8*gamepad1.right_stick_x;

        upperLeft.setPower
                (y_axis -x_axis -orientation);
        upperRight.setPower
                (y_axis + x_axis + orientation);
        lowerLeft.setPower
                (y_axis + x_axis - orientation);
        lowerRight.setPower
                (y_axis - x_axis + orientation);


        telemetry.addData("Forward:","%lf", y_axis);
        telemetry.addData("Strafe:", "%lf", x_axis);
        telemetry.addData("Turn:", "%lf", orientation);
        telemetry.update();
    }

}
