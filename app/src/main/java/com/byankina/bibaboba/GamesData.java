package com.byankina.bibaboba;

public class GamesData {
    private static final GameModel[] gamesArray = {
            new GameModel(
                    "Sonic the Hedgehog (1991)",
                    "$49.99",
                    "June 23, 1991",
                    "4.5",
                    R.drawable.sonic_1991_wall
            ),
            new GameModel(
                    "Sonic the Hedgehog 2",
                    "$49.99",
                    "November 21, 1992",
                    "4.7",
                    R.drawable.sonic_2_wall
            ),
            new GameModel(
                    "Sonic the Hedgehog 3",
                    "$54.99",
                    "February 2, 1994",
                    "4.6",
                    R.drawable.sonic_3_wall
            ),
            new GameModel(
                    "Sonic & Knuckles",
                    "$49.99",
                    "October 18, 1994",
                    "4.5",
                    R.drawable.sonicknuckles_wall_alter
            ),
            new GameModel(
                    "Sonic Adventure",
                    "$59.99",
                    "December 23, 1998",
                    "4.4",
                    R.drawable.sonic_adventure
            ),
            new GameModel(
                    "Sonic Adventure 2",
                    "$59.99",
                    "June 23, 2001",
                    "4.6",
                    R.drawable.sonic_adventure_2

            ),
            new GameModel(
                    "Sonic Heroes",
                    "$49.99",
                    "December 30, 2003",
                    "4.2",
                    R.drawable.sonic_heroes_wall
            ),
            new GameModel(
                    "Sonic Generations",
                    "$39.99",
                    "November 1, 2011",
                    "4.5",
                    R.drawable.sonic_generations_wall

            ),
            new GameModel(
                    "Sonic Mania",
                    "$19.99",
                    "August 15, 2017",
                    "4.8",
                    R.drawable.sonic_mania_wall
            ),
            new GameModel(
                    "Sonic Frontiers",
                    "$59.99",
                    "November 8, 2022",
                    "4.3",
                    R.drawable.sonic_frontiers_wall
            )
    };

    public static GameModel[] getGamesArray() {
        return gamesArray;
    }
}