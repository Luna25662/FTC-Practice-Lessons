package org.firstinspires.ftc.teamcode.lessons.teleOp;
//imports needed

//This might seem like a little, but
// we actually call all of our code
// from the hardwareChassis class.
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.ChassisHardware;

@TeleOp
public class SimplifiedTeleOp extends OpMode {

    //call the class here.
    //if you havent already, go
    // to HardWare and then go to
    // Efficent Init HW to create this
    // class before anything.
    //The name might change based on what you called it.
    ChassisHardware chassis = new ChassisHardware();

    @Override
    public void init(){
        //We call the init class, and just one line will do the chassis init for us.
        chassis.init(hardwareMap);
    }

    @Override
    public void loop(){
        //We call another method that lets us
        //control the chassis with gamepads.
        chassis.teleOpControl();

    }
}
