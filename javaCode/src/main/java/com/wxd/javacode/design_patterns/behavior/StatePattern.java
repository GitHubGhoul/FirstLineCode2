package com.wxd.javacode.design_patterns.behavior;

import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.plaf.nimbus.State;

public class StatePattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BotContext bot = new BotContext();
        for (;;) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String output = bot.chat(input);
            System.out.println(output.isEmpty() ? "(no reply)" : "< " + output);
        }
    }

    public interface State {

        String init();
        String reply(String input);

    }

    public static class DisconnectedState implements State {

        public String init() {
            return "Bye!";
        }

        public String reply(String input) {
            return "";
        }

    }

    public static class ConnectedState implements State {

        public String init() {
            return "Hello, I'm Bob.";
        }

        public String reply(String input) {
            if (input.endsWith("?")) {
                return "Yes. " + input.substring(0, input.length() - 1) + "!";
            }
            if (input.endsWith(".")) {
                return input.substring(0, input.length() - 1) + "!";
            }
            return input.substring(0, input.length() - 1) + "?";
        }

    }

    public static class BotContext {
        private State state = new DisconnectedState();

        public String chat(String input) {
            if ("hello".equalsIgnoreCase(input)) {
                // 收到hello切换到在线状态:
                state = new ConnectedState();
                return state.init();
            } else if ("bye".equalsIgnoreCase(input)) {
                // 收到bye切换到离线状态:
                state = new DisconnectedState();
                return state.init();
            }
            return state.reply(input);
        }
    }
}
