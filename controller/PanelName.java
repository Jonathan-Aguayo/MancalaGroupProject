package controller;

public enum PanelName {
    MENU("menu"), SETUP("setup"), GAME("game"), WINNER("winner"), QUIT("quit");

    private final String panelName;

    PanelName(String n) {
        panelName = n;
    }

    public String getName() {
        return panelName;
    }
}