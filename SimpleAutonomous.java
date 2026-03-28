





package org.firstinspires.ftc.teamcode;



//Imports you will need for your code to work.
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;




@Autonomous(name="AutoPrac")
public class SimpleAutonomous extends LinearOpMode {




    // Declare OpMode members for each of the 4 motors.
    //We will also declare a timer.
    //Think about this timer as a stopwatch
    //It keeps track of how many seconds passed,
    // and wil be useful to move your robot based on time.

    private DcMotor upperLeft;
    private DcMotor upperRight;
    private DcMotor lowerLeft;
    private DcMotor lowerRight;





    @Override
    public void runOpMode() {
//Here we initialize our motors so the brain of the robot knows what motor is which.
        upperLeft = hardwareMap.get(DcMotor.class, ("upperLeft"));
        upperRight = hardwareMap.get(DcMotor.class, ("upperRight"));
        lowerLeft = hardwareMap.get(DcMotor.class, ("lowerLeft"));
        lowerRight = hardwareMap.get(DcMotor.class, ("lowerRight"));


        //this just shows text on the screen
        telemetry.addData("Status", "Ready For Patitos!");
        telemetry.update();


        //waitForStart basically waits until we press start to run everything
        waitForStart();
        //The first number we put inside of the method drive is
        //Power, then how many ticks to move forward
        //Then how many ticks to strafe
        //Then how many ticks to turn
        //It isn't recommended to move two ways at the same time
        //This is because it won't move the way intended
        drive(1,500,0,0);
        //Sleep basically stops the complete robot from moving in milliseconds
        //So 1000 is just one second
        //You can use timer based Autonomous to not have to do this
        //in case you want to move something else when stopping
        sleep(1000);
        drive(0.7,0,0,100);
        sleep(2000);
        drive(0.8,0,6767,0);
        sleep(30000);
    }

    //This is an encoder based driving setup that we can easily manipulate
    // to move our robot one way.
    //An encoder counts based on rotations in the motor
    //We call this unit of counting, TICKS

    public void drive(double power, int forward, int strafe, int turn) {
        //Here we make sure to reset the encoders in case
        // they weren't by the motor itself
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







//We set the robot to sleep for 100 milliseconds so it doesn't get tangled when moving to the next set of instructions.
        sleep(100);
    }
}


