package com.settlementGame.game;

import com.settlementGame.R;
import com.settlementGame.game.gameObject.GameObject;
import com.settlementGame.game.gameObject.Materials;
import com.settlementGame.game.gameObject.Resource;

public class Recipe {

    public static Recipe mineStone = new Recipe(SettlementGame.AGame.getResources().getStringArray(R.array.task_names)[0], new Materials(), 10,
            new Materials(new GameObject[]{Resource.createResource(Resource.STONE)}, new int[]{10}));
    public static Recipe mineOre = new Recipe(SettlementGame.AGame.getResources().getStringArray(R.array.task_names)[1], new Materials(), 10,
            new Materials(new GameObject[]{Resource.createResource(Resource.ORE)}, new int[]{2}));
    public static Recipe smeltOre = new Recipe(SettlementGame.AGame.getResources().getStringArray(R.array.task_names)[2], new Materials(new GameObject[]{Resource.ORE}, new int[]{5}), 10,
            new Materials(new GameObject[]{Resource.createResource(Resource.BAR)}, new int[]{1}));

    public String name;
    public int progression = 0, progressionNeeded, repeatCount = 1;
    public Materials components, results;
    public boolean recipeHidden = false;

    private Recipe(String name, Materials components, int progressionNeeded, Materials results){
        this.name = name;
        this.components = components;
        this.progressionNeeded = progressionNeeded;
        this.results = results;
    }

    public static Recipe createRecipe(Recipe t){
        Recipe tRet = new Recipe(t.name, t.components, t.progressionNeeded, t.results);
        return tRet;
    }

}