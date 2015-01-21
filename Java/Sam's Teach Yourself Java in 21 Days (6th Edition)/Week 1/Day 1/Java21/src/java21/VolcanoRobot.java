class VolcanoRobot {
    // create instance variables
    String status;
    int speed;
    float temperature;
    
    // create instance method
    void checkTemperature() {
        if (temperature > 660) {
            status = "returning home";
            speed = 5;
        }
    }
    
    // create instance method
    void showAttributes() {
        System.out.println("Status: " + status);
        System.out.println("Speed: " + speed);
        System.out.println("Temperature: " + temperature);
    }
}