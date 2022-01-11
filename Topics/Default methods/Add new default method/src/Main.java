interface Flying {
    // returns height of flying in meters

    int METERS_IN_KILOS = 1000;

    int getHeight();

    // implements a default method getHeightInKm that returns height of flying in km as int type
    default int getHeightInKm() {
        return getHeight() / METERS_IN_KILOS;
    }
}