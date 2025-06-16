package br.com.gracilianoog;

import br.com.gracilianoog.ui.custom.screen.MainScreen;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class UIMain {
    public static void main(String[] args) {
        final Map<String, String> gameConfig = Stream.of(args).collect(toMap(
                k -> k.split(";")[0],
                v -> v.split(";")[1]
        ));

        MainScreen mainScreen = new MainScreen(gameConfig);
        mainScreen.buildMainScreen();
    }
}
