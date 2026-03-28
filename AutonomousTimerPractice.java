





package org.firstinspires.ftc.teamcode;



//Imports you will need for your code to work.
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;




@Autonomous(name="AutoPrac")
public class AutonomousTimerPractice extends LinearOpMode {




    // Declare OpMode members for each of the 4 motors.
    //We will also declare a timer.
    //Think about this timer as a stopwatch
    //It keeps track of how many seconds passed,
    // and wil be useful to move your robot based on time.
    private ElapsedTime timer;
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


        waitForStart();
        //We make sure to reset timer because
        // it starts running when we initialize which we don't want.
        //You always want to start with zero to keep track, so
        //timer.reset() does exactly that.
        timer.reset();
        //Here we set this chunk of code to repeat until the timer is greater than 4
        while(timer.seconds() <= 4){
            //Telemetry is just showing text on the screen on how any seconds timer is at
            //We then update the telemetry to keep in track to the current telemetry
            telemetry.addData("Timer:", timer.seconds());
            telemetry.update();
            //This drive is basically a function/method
            // that we call from the very bottom that we
            // built to easily move our robot however we want using ticks
            //If you're not familiar with this, go back to Simple Autonomous,
            //and learn a bit on methods and functions to understand what is really happening
            drive(1,100,0,0);
        }
        //The timer will then reset again to make it easier for us to keep track again from 0
timer.reset();

        while(timer.seconds() <=3){
            telemetry.addData("Timer:", timer.seconds());
            telemetry.update();

            drive(.8,0,300,0);

        }
        //Sets a 1 second delay because we said to run code while
        // timer is less than or equal to five and also greater than or equal to 1 seconds.
        //You can play around with these delays to stop your robot to shoot or when neeeded.
        timer.reset();

        while(timer.seconds() <=5 && timer.seconds() >=1){
            telemetry.addData("Timer:", timer.seconds());
            telemetry.update();
            drive(0.5,0,0,300);


        }

    }


    //This is an encoder based driving setup that we can easily manipulate
    // to move our robot one way.
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



//This sets the power of the moter based on the user-input
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


