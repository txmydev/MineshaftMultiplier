package net.skulluhc.mineshaftmultiplier;

import net.minecraft.server.v1_7_R4.MathHelper;
import net.minecraft.server.v1_7_R4.StructureGenerator;
import net.minecraft.server.v1_7_R4.StructureStart;
import net.minecraft.server.v1_7_R4.WorldGenMineshaftStart;

import java.util.Iterator;
import java.util.Map;

public class CustomWorldGenMineshaft extends StructureGenerator {
    private double e = 0.004D;

    public String a() {
        return "Mineshaft";
    }

    public CustomWorldGenMineshaft(double multiplier) {
        this.e *= multiplier;

        System.out.println("Hellouda :D");
    }

    protected boolean a(int var1, int var2) {
        System.out.println("CUSTOM MINESHAFT GEN IS WORKING MTE!");
        return this.b.nextDouble() < this.e && this.b.nextInt(80) < Math.max(Math.abs(var1), Math.abs(var2));
    }

    protected StructureStart b(int var1, int var2) {
        return new WorldGenMineshaftStart(this.c, this.b, var1, var2);
    }
}