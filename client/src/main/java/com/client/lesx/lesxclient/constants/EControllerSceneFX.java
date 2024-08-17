package com.client.lesx.lesxclient.constants;

public enum EControllerSceneFX {
    INITIAL_VIEW_FXML{
        @Override
        public String getFileName() {
            return "initial_view.fxml";
        }
    },
    MAIN_MENU{
        @Override
        public String getFileName() {
            return "main_menu.fxml";
        }
    },
    PROPERTY_SHEETS {
        @Override
        public String getFileName() {
            return "property_view.fxml";
        }
    };

    public abstract String getFileName();
}
