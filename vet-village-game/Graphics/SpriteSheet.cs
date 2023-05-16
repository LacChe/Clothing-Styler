using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;

namespace vet
{
    class SpriteSheet
    {

        public static SpriteFont inventoryFont;
        // public static SpriteFont chinese;

        public static Texture2D sandTiles;
        public static Texture2D player0;
        public static Texture2D npc0;
        public static Texture2D dog0;


        public static Texture2D liHe;
        public static Texture2D zuoQing;

        public static Texture2D upperClothes;
        public static Texture2D furniture;
        public static Texture2D chest;

        public static Texture2D crops;
        public static Texture2D herbs;

        public static Texture2D wallPaper;
        public static Texture2D flooring;

        public static Texture2D chestInventory;
        public static Texture2D storeInventory;
        public static Texture2D inventoryBack;
        public static Texture2D selectionBox;
        public static Texture2D backPack;
        public static Texture2D time;
        public static Texture2D statusIcon;


        public static Texture2D mapBridgeEast;
        public static Texture2D mapCavePond;
        public static Texture2D mapField;
        public static Texture2D mapMountainLower;
        public static Texture2D mapMountainUpper;
        public static Texture2D mapRailroad;
        public static Texture2D mapRailroadNE;
        public static Texture2D mapRailroadSE;
        public static Texture2D mapTownCenter;

        public static Texture2D mapMountainLower1;
        public static Texture2D mapMountainUpper1;
        public static Texture2D mapRailroad1;
        public static Texture2D mapRailroadSE1;
        public static Texture2D mapTownCenter1;
        public static Texture2D mapTownCenter2;

        public static Texture2D mapBridgeEastPassible;
        public static Texture2D mapCavePondPassible;
        public static Texture2D mapFieldPassible;
        public static Texture2D mapMountainLowerPassible;
        public static Texture2D mapMountainUpperPassible;
        public static Texture2D mapRailroadPassible;
        public static Texture2D mapRailroadNEPassible;
        public static Texture2D mapRailroadSEPassible;
        public static Texture2D mapTownCenterPassible;

        public static Texture2D buildingInterior0;
        public static Texture2D buildingInterior0Passible;
        public static Texture2D buildingInteriorVet;
        public static Texture2D buildingInteriorVetOver;
        public static Texture2D buildingInteriorVetPassible;

        public static Texture2D buildingInteriorDoctor;
        public static Texture2D buildingInteriorDoctorPassible;

        // tempspot
        public static Texture2D mapMountainLowerOver;
        public static Texture2D mapMountainLower1Over;
        public static Texture2D mapFieldOver;
        public static Texture2D mapTownCenterOver;
        public static Texture2D mapTownCenter1Over;
        public static Texture2D mapTownCenter2Over;
        public static void Init(ContentManager Content)
        {
            inventoryFont = Content.Load<SpriteFont>("fonts/inventoryFont");
            // chinese = Content.Load<SpriteFont>("chinese");

            sandTiles = Content.Load<Texture2D>("maps/sandTiles");
            player0 = Content.Load<Texture2D>("movingObjects/player0");
            npc0 = Content.Load<Texture2D>("movingObjects/npc0");
            dog0 = Content.Load<Texture2D>("movingObjects/dog0");

            liHe = Content.Load<Texture2D>("movingObjects/liHe");
            zuoQing = Content.Load<Texture2D>("movingObjects/zuoQing");

            upperClothes = Content.Load<Texture2D>("items/upperClothes");
            furniture = Content.Load<Texture2D>("items/furniture");
            chest = Content.Load<Texture2D>("items/chest");

            crops = Content.Load<Texture2D>("items/crops");
            herbs = Content.Load<Texture2D>("items/herbs");

            wallPaper = Content.Load<Texture2D>("items/wallPaper");
            flooring = Content.Load<Texture2D>("items/flooring");

            inventoryBack = Content.Load<Texture2D>("UI/inventory");
            selectionBox = Content.Load<Texture2D>("UI/selectionBox");
            chestInventory = Content.Load<Texture2D>("UI/chestInventory");
            storeInventory = Content.Load<Texture2D>("UI/storeInventory");
            backPack = Content.Load<Texture2D>("UI/backPack");
            time = Content.Load<Texture2D>("UI/time");
            statusIcon = Content.Load<Texture2D>("UI/statusIcon");

            mapBridgeEast = Content.Load<Texture2D>("maps/mapBridgeEast");
            mapCavePond = Content.Load<Texture2D>("maps/mapCavePond");
            mapField = Content.Load<Texture2D>("maps/mapField");
            mapMountainLower = Content.Load<Texture2D>("maps/mapMountainLower");
            mapMountainUpper = Content.Load<Texture2D>("maps/mapMountainUpper");
            mapRailroad = Content.Load<Texture2D>("maps/mapRailroad");
            mapRailroadNE = Content.Load<Texture2D>("maps/mapRailroadNE");
            mapRailroadSE = Content.Load<Texture2D>("maps/mapRailroadSE");
            mapTownCenter = Content.Load<Texture2D>("maps/mapTownCenter");

            mapMountainLower1 = Content.Load<Texture2D>("maps/mapMountainLower1");
            mapMountainUpper1 = Content.Load<Texture2D>("maps/mapMountainUpper1");
            mapRailroad1 = Content.Load<Texture2D>("maps/mapRailroad1");
            mapRailroadSE1 = Content.Load<Texture2D>("maps/mapRailroadSE1");
            mapTownCenter1 = Content.Load<Texture2D>("maps/mapTownCenter1");
            mapTownCenter2 = Content.Load<Texture2D>("maps/mapTownCenter2");

            mapBridgeEastPassible = Content.Load<Texture2D>("maps/mapBridgeEastPassible");
            mapCavePondPassible = Content.Load<Texture2D>("maps/mapCavePondPassible");
            mapFieldPassible = Content.Load<Texture2D>("maps/mapFieldPassible");
            mapMountainLowerPassible = Content.Load<Texture2D>("maps/mapMountainLowerPassible");
            mapMountainUpperPassible = Content.Load<Texture2D>("maps/mapMountainUpperPassible");
            mapRailroadPassible = Content.Load<Texture2D>("maps/mapRailroadPassible");
            mapRailroadNEPassible = Content.Load<Texture2D>("maps/mapRailroadNEPassible");
            mapRailroadSEPassible = Content.Load<Texture2D>("maps/mapRailroadSEPassible");
            mapTownCenterPassible = Content.Load<Texture2D>("maps/mapTownCenterPassible");

            buildingInterior0 = Content.Load<Texture2D>("maps/buildingInterior0");
            buildingInterior0Passible = Content.Load<Texture2D>("maps/buildingInterior0Passible");
            buildingInteriorVet = Content.Load<Texture2D>("maps/buildingInteriorVet");
            buildingInteriorVetOver = Content.Load<Texture2D>("maps/buildingInteriorVetOver");
            buildingInteriorVetPassible = Content.Load<Texture2D>("maps/buildingInteriorVetPassible");
            buildingInteriorDoctor = Content.Load<Texture2D>("maps/buildingInteriorDoctor");
            buildingInteriorDoctorPassible = Content.Load<Texture2D>("maps/buildingInteriorDoctorPassible");

            // temp spot
            mapMountainLowerOver = Content.Load<Texture2D>("maps/mapMountainLowerOver");
            mapMountainLower1Over = Content.Load<Texture2D>("maps/mapMountainLower1Over");
            mapFieldOver = Content.Load<Texture2D>("maps/mapFieldOver");
            mapTownCenterOver = Content.Load<Texture2D>("maps/mapTownCenterOver");
            mapTownCenter1Over = Content.Load<Texture2D>("maps/mapTownCenter1Over");
            mapTownCenter2Over = Content.Load<Texture2D>("maps/mapTownCenter2Over");
        }


        public static SpriteFont GetFont(string name)
        {
            name = name.ToLower();
            switch (name)
            {
                case "inventoryfont":
                    return inventoryFont;
                case "chinese":
                    // return chinese;
                default:
                    return inventoryFont;
            }
        }


        public static Texture2D GetSheet(string name)
        {
            name = name.ToLower();
            switch (name)
            {
                case "sandtiles":
                    return sandTiles;

                // characters
                case "player0":
                    return player0;
                case "npc0":
                    return npc0;
                case "dog0":
                    return dog0;

                // npc
                case "lihe":
                    return liHe;
                case "zuoqing":
                    return zuoQing;

                // clothing
                case "upperclothes":
                    return upperClothes;
                case "furniture":
                    return furniture;
                case "chest":
                    return chest;

                // food items
                case "crops":
                    return crops;
                case "herbs":
                    return herbs;

                // clothing
                case "wallpaper":
                    return wallPaper;
                case "flooring":
                    return flooring;

                // UI
                case "selectionbox":
                    return selectionBox;
                case "inventory":
                    return inventoryBack;
                case "chestinventory":
                    return chestInventory;
                case "storeinventory":
                    return storeInventory;
                case "backpack":
                    return backPack;
                case "time":
                    return time;
                case "statusicon":
                    return statusIcon;

                // map
                case "mapbridgeeast":
                    return mapBridgeEast;
                case "mapcavepond":
                    return mapCavePond;
                case "mapfield":
                    return mapField;
                case "mapmountainlower":
                    return mapMountainLower;
                case "mapmountainupper":
                    return mapMountainUpper;
                case "maprailroad":
                    return mapRailroad;
                case "maprailroadne":
                    return mapRailroadNE;
                case "maprailroadse":
                    return mapRailroadSE;
                case "maptowncenter":
                    return mapTownCenter;

                case "mapmountainlower1":
                    return mapMountainLower1;
                case "mapmountainupper1":
                    return mapMountainUpper1;
                case "maprailroad1":
                    return mapRailroad1;
                case "maprailroadse1":
                    return mapRailroadSE1;
                case "maptowncenter1":
                    return mapTownCenter1;
                case "maptowncenter2":
                    return mapTownCenter2;

                case "mapbridgeeastpassible":
                    return mapBridgeEastPassible;
                case "mapcavepondpassible":
                    return mapCavePondPassible;
                case "mapfieldpassible":
                    return mapFieldPassible;
                case "mapmountainlowerpassible":
                    return mapMountainLowerPassible;
                case "mapmountainupperpassible":
                    return mapMountainUpperPassible;
                case "maprailroadpassible":
                    return mapRailroadPassible;
                case "maprailroadnepassible":
                    return mapRailroadNEPassible;
                case "maprailroadsepassible":
                    return mapRailroadSEPassible;
                case "maptowncenterpassible":
                    return mapTownCenterPassible;


                case "buildinginterior0":
                    return buildingInterior0;
                case "buildinginterior0passible":
                    return buildingInterior0Passible;
                case "buildinginteriorvet":
                    return buildingInteriorVet;
                case "buildinginteriorvetover":
                    return buildingInteriorVetOver;
                case "buildinginteriorvetpassible":
                    return buildingInteriorVetPassible;
                case "buildinginteriordoctor":
                    return buildingInteriorDoctor;
                case "buildinginteriordoctorpassible":
                    return buildingInteriorDoctorPassible;

                // te,[p spot

                case "mapmountainlowerover":
                    return mapMountainLowerOver;
                case "mapmountainlower1over":
                    return mapMountainLower1Over;
                case "mapfieldover":
                    return mapFieldOver;
                case "maptowncenterover":
                    return mapTownCenterOver;
                case "maptowncenter1over":
                    return mapTownCenter1Over;
                case "maptowncenter2over":
                    return mapTownCenter2Over;

                default:
                    return sandTiles;
            }
        }
    }
}
