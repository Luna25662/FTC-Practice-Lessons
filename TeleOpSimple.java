package org.firstinspires.ftc.teamcode;
//Imports you need to make code function
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//You call if it's a teleOp or Autonomous with the @
@TeleOp
//Here is the main class that will hold the initialize,
// and loop functions.
public class TeleOpSimple extends OpMode {
    //Here we state our motors and components.
    //These motors are name based
    // on where they are placed on the robot.
    DcMotor upperLeft,
            upperRight,
            lowerLeft,
            lowerRight;
 //This is the initialization function
    //On the driverHub, there will be an
    //Init button that will execute this chunk of code
    public void init(){
        //hardwareMap is basically telling the brain
        // of the robot which motor is which.
        lowerLeft = hardwareMap.dcMotor.get("lowerLeft");
        lowerRight = hardwareMap.dcMotor.get("lowerRight");
        upperLeft = hardwareMap.dcMotor.get("upperLeft");
        upperRight = hardwareMap.dcMotor.get("upperRight");
        //Here we set the left wheels reversed
        // since they are backbacks in real life.
        lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        upperLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    //This is the loop function that will
    // constantly check for inputs, funtions,
    //and robot movement.
    public void loop(){
        //These variable represents the joystick with
        //the amount of maximum power it can achieve.
        //1 is the maximum power, 0 is no movement,
        //and -1 moving the motor in reverse.
        double forward = 0.8*gamepad1.left_stick_y;
        double strafe = 0.8*gamepad1.left_stick_x;
        double turn = 0.8*gamepad1.right_stick_x;

        //These represent how the robot moves
        //depending on the joystick movement.
        //Look at the GoBilda Mecanum Drive Diagram
        //Then look at the arrows, and you'll notice
        //how these variables represent the wheels
        //depending on the sort of movement.
        upperLeft.setPower(forward-strafe-turn);
        upperRight.setPower(forward+strafe+turn);
        lowerLeft.setPower(forward + strafe - turn);
        lowerRight.setPower(forward - strafe + turn);


        //Now you will have a simple robot that you can move around with :3
    }

}
