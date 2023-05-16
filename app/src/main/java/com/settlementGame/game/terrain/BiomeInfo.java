package com.settlementGame.game.terrain;

import com.settlementGame.R;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.gameObject.Flooring;
import com.settlementGame.game.gameObject.Tile;

import java.util.Random;

public class BiomeInfo {

    private static Random rand;

    public static BiomeInfo biomeTypeSelected;
    public static BiomeInfo.BiomeType[][] biomeGrid;

    public static int biomeGridSize = 6;

    public static int biomeSpriteWidth = 24, biomeSpriteHeight = 36, biomeSpriteDrawOffset = 12;

    public static final BiomeInfo HILLS = new BiomeInfo(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[0], 8, 4, 4, 3, 0, 5, Tile.DIRT, Flooring.GRASS);
    public static final BiomeInfo MOUNTAINS = new BiomeInfo(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[1],12, 6, 3, 1, 0, 2, Tile.DIRT, Flooring.GRASS);
    public static final BiomeInfo PLAINS = new BiomeInfo(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[2],2, 2, 2, 5, 0, 12, Tile.DIRT, Flooring.GRASS);
    public static final BiomeInfo SAVANNA = new BiomeInfo(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[3],2, 2, 1, 1, 9, 9, Tile.SAND, Flooring.GRASS);
    public static final BiomeInfo DESERT = new BiomeInfo(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[4],2, 2, 0, 1, 18, 1, Tile.SAND, Flooring.GRASS);
    public static final BiomeInfo ISLAND = new BiomeInfo(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[5],1, 1, 2, 12, 6, 2, Tile.DIRT, Flooring.GRASS);
    public static final BiomeInfo BEACH = new BiomeInfo(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[6],2, 2, 2, 8, 6, 4, Tile.DIRT, Flooring.GRASS);
    public static final BiomeInfo FOREST = new BiomeInfo(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[7],2, 2, 16, 3, 0, 1, Tile.DIRT, Flooring.GRASS);
    public static final BiomeInfo RAINFOREST = new BiomeInfo(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[8],2, 2, 16, 3, 0, 1, Tile.DIRT, Flooring.GRASS);
    public static final BiomeInfo TUNDRA = new BiomeInfo(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[9],4, 2, 6, 3, 0, 9, Tile.DIRT, Flooring.SNOW);

    public enum BiomeType{
        STONE, ORE, WOODS, WATER, SAND, FIELD, LAVASTONE, LAVAORE, VOID;

        public static int getIndex(BiomeType t){
            switch(t){
                case STONE:
                    return 0;
                case ORE:
                    return 1;
                case WOODS:
                    return 2;
                case WATER:
                    return 3;
                case SAND:
                    return 4;
                case FIELD:
                    return 5;
                case LAVASTONE:
                    return 6;
                case LAVAORE:
                    return 7;
                case VOID:
                default:
                    return -1;
            }
        }
    }

    public String name;

    public int stoneAmt;
    public int oreAmt;
    public int woodsAmt;
    public int waterAmt;
    public int sandAmt;
    public int fieldAmt;

    public Tile defaultTile;
    public Flooring defaultFlooring;

    public static void init(){
        rand = new Random();
        biomeTypeSelected = BiomeInfo.HILLS;
        biomeGrid = new BiomeInfo.BiomeType[biomeGridSize][biomeGridSize];
        genBiome(BiomeInfo.HILLS);
    }

    public BiomeInfo(String n, int stone, int ore, int woods, int water, int sand, int field, Tile defT, Flooring defF){
        name = n;
        stoneAmt = stone;
        oreAmt = ore;
        woodsAmt = woods;
        waterAmt = water;
        sandAmt = sand;
        fieldAmt = field;
        defaultTile = defT;
        defaultFlooring = defF;
    }

    public static void genBiome(BiomeInfo type){
        for(int i = 0; i < biomeGridSize; i++){
            for(int j = 0; j < biomeGridSize; j++){
                BiomeInfo.biomeGrid[i][j] = BiomeInfo.BiomeType.VOID;
            }
        }

        int emptySpotsLeft = 36;

        // gen water
        int r = rand.nextInt(2);
        for(int i = 0; i < Math.max(type.waterAmt - r, 1);){
            int x = rand.nextInt(biomeGridSize);
            int y = rand.nextInt(biomeGridSize);
            if(inGridBounds(x, y) && BiomeInfo.biomeGrid[x][y] == BiomeInfo.BiomeType.VOID){
                BiomeInfo.biomeGrid[x][y] = BiomeInfo.BiomeType.WATER;
                i++;
                emptySpotsLeft--;
                if(emptySpotsLeft == 0){
                    genVolcano();
                    return;
                }
            }
        }

        // gen sandy
        r = rand.nextInt(2);
        for(int i = 0; i < Math.max(type.sandAmt - r, type.defaultTile == Tile.SAND ? 1 : 0);){
            int x = rand.nextInt(biomeGridSize);
            int y = rand.nextInt(biomeGridSize);
            if(inGridBounds(x, y) && BiomeInfo.biomeGrid[x][y] == BiomeInfo.BiomeType.VOID){
                BiomeInfo.biomeGrid[x][y] = BiomeInfo.BiomeType.SAND;
                i++;
                emptySpotsLeft--;
                if(emptySpotsLeft == 0){
                    genVolcano();
                    return;
                }
            }
        }

        // gen stone
        r = rand.nextInt(2);
        for(int i = 0; i < Math.max(type.stoneAmt - r, 1);){
            int x = rand.nextInt(biomeGridSize);
            int y = rand.nextInt(biomeGridSize);
            if(inGridBounds(x, y) && BiomeInfo.biomeGrid[x][y] == BiomeInfo.BiomeType.VOID){
                BiomeInfo.biomeGrid[x][y] = BiomeInfo.BiomeType.STONE;
                i++;
                emptySpotsLeft--;
                if(emptySpotsLeft == 0){
                    genVolcano();
                    return;
                }
            }
        }

        // gen ore
        r = rand.nextInt(2);
        for(int i = 0; i < Math.max(type.oreAmt - r, 1);){
            int x = rand.nextInt(biomeGridSize);
            int y = rand.nextInt(biomeGridSize);
            if(inGridBounds(x, y) && BiomeInfo.biomeGrid[x][y] == BiomeInfo.BiomeType.VOID){
                BiomeInfo.biomeGrid[x][y] = BiomeInfo.BiomeType.ORE;
                i++;
                emptySpotsLeft--;
                if(emptySpotsLeft == 0){
                    genVolcano();
                    return;
                }
            }
        }

        // gen field
        r = rand.nextInt(2);
        for(int i = 0; i < Math.max(type.fieldAmt - r, type.defaultTile == Tile.DIRT ? 1 : 0);){
            int x = rand.nextInt(biomeGridSize);
            int y = rand.nextInt(biomeGridSize);
            if(inGridBounds(x, y) && BiomeInfo.biomeGrid[x][y] == BiomeInfo.BiomeType.VOID){
                BiomeInfo.biomeGrid[x][y] = BiomeInfo.BiomeType.FIELD;
                i++;
                emptySpotsLeft--;
                if(emptySpotsLeft == 0){
                    genVolcano();
                    return;
                }
            }
        }

        // gen woods
        r = rand.nextInt(2);
        for(int i = 0; i < Math.max(type.woodsAmt - r, 1);){
            int x = rand.nextInt(biomeGridSize);
            int y = rand.nextInt(biomeGridSize);
            if(inGridBounds(x, y) && BiomeInfo.biomeGrid[x][y] == BiomeInfo.BiomeType.VOID){
                BiomeInfo.biomeGrid[x][y] = BiomeInfo.BiomeType.WOODS;
                i++;
                emptySpotsLeft--;
                if(emptySpotsLeft == 0){
                    genVolcano();
                    return;
                }
            }
        }

        for(int e = 0; e < emptySpotsLeft; e++){
            for(int i = 0; i < biomeGridSize; i++){
                for(int j = 0; j < biomeGridSize; j++){
                    if (inGridBounds(i, j) && BiomeInfo.biomeGrid[i][j] == BiomeInfo.BiomeType.VOID){
                        BiomeInfo.biomeGrid[i][j] = type.defaultTile == Tile.DIRT ? BiomeType.FIELD : BiomeType.SAND;
                    }
                }
            }
        }
        genVolcano();
    }

    private static void genVolcano(){
        // gen volcano
        for(int i = 0; i < biomeGridSize; i++){
            for(int j = 0; j < biomeGridSize; j++){
                if (BiomeInfo.biomeGrid[i][j] == BiomeInfo.BiomeType.STONE && rand.nextInt(12) == 0){
                    BiomeInfo.biomeGrid[i][j] = BiomeInfo.BiomeType.LAVASTONE;
                }
                if (BiomeInfo.biomeGrid[i][j] == BiomeInfo.BiomeType.ORE && rand.nextInt(12) == 0){
                    BiomeInfo.biomeGrid[i][j] = BiomeInfo.BiomeType.LAVAORE;
                }
            }
        }
    }

    private static boolean inGridBounds(int x, int y){
        return !((x == 0) && (y == 0 || y == 1 || y == 4 || y == 5)
                || (x == 1) && (y == 0 || y == 5)
                || (x == 4) && (y == 0 || y == 5)
                || (x == 5) && (y == 0 || y == 1 || y == 4 || y == 5));
    }
}