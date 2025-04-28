package com.jobportal.utility;

public class Data {
    public static String getMessageBody(String otp, String name) {
        return "<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head><meta charset='UTF-8'><title>Your OTP Code</title>"
                + "<style>"
                + "body{font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;background-color:#fff8e1;margin:0;padding:0;}"
                + ".container{max-width:600px;margin:40px auto;background:#ffffff;border-radius:12px;"
                + "box-shadow:0 4px 10px rgba(0,0,0,0.15);padding:40px;}"
                + ".header{text-align:center;padding-bottom:20px;border-bottom:2px solid #ffd700;}"
                + ".header h1{margin:0;font-size:28px;color:#b8860b;}" // Or (GoldenRod)
                + ".content{margin-top:30px;text-align:center;}"
                + ".content p{font-size:16px;color:#333;margin:10px 0;}"
                + ".otp-code{display:inline-block;margin:20px 0;font-size:36px;color:#ffbf00;font-weight:bold;letter-spacing:6px;}"
                + ".footer{margin-top:40px;font-size:12px;color:#999;text-align:center;}"
                + ".license{margin-top:10px;font-size:14px;color:#b8860b;font-weight:bold;}"
                + "</style></head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'><h1>OTP Verification</h1></div>"
                + "<div class='content'>"
                + "<p>Hello <strong>" + name + "</strong>,</p>"
                + "<p>Please use the OTP code below to verify your email address:</p>"
                + "<div class='otp-code'>" + otp + "</div>"
                + "<p>This code will expire in 5 minutes.</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>If you did not request this code, you can safely ignore this email.</p>"
                + "<div class='license'>Powered by Job Portal</div>"
                + "</div>"
                + "</div>"
                + "</body></html>";
    }
}
