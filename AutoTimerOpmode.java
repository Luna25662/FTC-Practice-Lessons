package org.firstinspires.ftc.teamcode;


//Imports you need for code to function
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


//This is Autonomous in OpMode instead of linear opmode
//This means there's a loop we can work with, but
//this examplar is just an exact copy of the other auto.
@Autonomous
public class AutoTimerOpmode extends OpMode {
    //Timer with motors
    ElapsedTime timer;
    DcMotor upperLeft,
            upperRight,
            lowerLeft,
            lowerRight;

    //Here you initialize motors and parts
    //You tell the brain of the robot which
    // parts are which.
    public void init(){
        upperLeft = hardwareMap.dcMotor.get("upperLeft");
        upperRight = hardwareMap.dcMotor.get("upperRight");
        lowerLeft = hardwareMap.dcMotor.get("lowerLeft");
        lowerRight = hardwareMap.dcMotor.get("lowerRight");
        //Reverse left wheels so they move correctly
        upperRight.setDirection(DcMotorSimple.Direction.REVERSE);
        upperLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    //This loop repeats to check repeatedly this chunk of code
    //The exception is that we will use while
    //loops to stop the constact checking.
    public void loop(){
        //reset timer so it start from 0
        timer.reset();
        //drive robot forward at 0.8 power for 3 seconds
        while(timer.seconds() <=3){
            drive(0.8,200,0,0);
        }

        timer.reset();
        while(timer.seconds() <=2 && timer.seconds() >=1){
            drive(0.8,0,400,0);
        }
        //This while loop goes on for 1000 seconds
        // to stop the robot until autonomous ends.
        //If you don't, the
        timer.reset();
        while(timer.seconds() <=1000 ){
            drive(0,0,0,0);
        }
    }

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

}
