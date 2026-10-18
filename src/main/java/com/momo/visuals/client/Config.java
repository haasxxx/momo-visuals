package com.momo.visuals.client;

public class Config {
    // Туман
    public static boolean fogEnabled = true;
    public static float fogR = 0.8f, fogG = 0.2f, fogB = 0.2f;

    // Небо
    public static boolean skyEnabled = true;
    public static float skyTopR = 0.1f, skyTopG = 0.1f, skyTopB = 0.6f;
    public static float skyBotR = 0.8f, skyBotG = 0.2f, skyBotB = 0.2f;

    // Частицы
    public static boolean ambientParticles = true;
    public static int ambientShape = 0;
    public static float ambientR = 0.5f, ambientG = 0.8f, ambientB = 1.0f;

    public static boolean hitParticles = true;
    public static int hitShape = 0;
    public static float hitR = 1.0f, hitG = 0.1f, hitB = 0.1f;

    // ViewModel (Руки)
    public static boolean viewModelEnabled = false;
    public static float vmPosX = 0.0f, vmPosY = 0.0f, vmPosZ = 0.0f;
    public static float vmRotX = 0.0f, vmRotY = 0.0f, vmRotZ = 0.0f;
}