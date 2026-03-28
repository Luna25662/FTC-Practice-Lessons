package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name = "TeleLuna")
public class TeleOpPractice extends OpMode{


private DcMotor
        upperLeft,
        upperRight,
        lowerLeft,
        lowerRight;

    public void init(){
    upperLeft = hardwareMap.dcMotor.get("upperLeft");
    upperRight = hardwareMap.dcMotor.get("upperRight");
    lowerLeft = hardwareMap.dcMotor.get("lowerLeft");
    lowerRight = hardwareMap.dcMotor.get("lowerRight");

    upperLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);



    }
    public void loop(){

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
