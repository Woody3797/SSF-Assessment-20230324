package ibf2022.batch2.ssf.frontcontroller.model;

import java.util.Random;

public class Captcha {
    
    private int num1 = 0;
    private int num2 = 0;
    private String[] operator = {"+", "-", "*", "/"};
    private int response;

    public Captcha() {
    }

    public Captcha(int num1, int num2, String[] operator) {
        this.num1 = getRandom1();
        this.num2 = getRandom2();
        this.operator = operator;
    }

    public int getNum1() {
        return num1;
    }
    public void setNum1(int num1) {
        this.num1 = num1;
    }
    public int getNum2() {
        return num2;
    }
    public void setNum2(int num2) {
        this.num2 = num2;
    }
    public String[] getOperator() {
        return operator;
    }
    public void setOperator(String[] operator) {
        this.operator = operator;
    }
    public int getResponse() {
        return response;
    }
    public void setResponse(int response) {
        this.response = response;
    }
    

    public int getRandom1() {
        Random rand = new Random();
        this.num1 = rand.nextInt(50) + 1;
        return this.num1;
    }
    public int getRandom2() {
        Random rand = new Random();
        this.num2 = rand.nextInt(50) + 1;
        return this.num2;
    }

    public String getRandEqn() {
        int result = 0;
        Random rand = new Random();
        int i = rand.nextInt(4);
        switch (this.operator[i]) {
            case "+":
                result = getRandom1() + getRandom2();
                break;
                case "-":
                result = getRandom1() - getRandom2();
                break;
                case "*":
                result = getRandom1() * getRandom2();
                break;
                case "/":
                result = getRandom1() / getRandom2();
                break;
            default:
                break;
        }
        String capt = String.valueOf(this.num1) + this.operator[i] + String.valueOf(this.num2);
        return capt;
    }

    
}
