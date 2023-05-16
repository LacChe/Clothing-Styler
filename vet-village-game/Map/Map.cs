using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace vet
{
    class Map
    {
        protected static string[] mapFileNames = new string[] { //
            "mapBridgeEast", // 0
            "mapCavePond", // 1
            "mapField", // 2
            "mapMountainLower", // 3
            "mapMountainUpper", // 4
            "mapRailroad", // 5
            "mapRailroadNE", // 6
            "mapRailroadSE", // 7
            "mapTownCenter", // 8

            "mapMountainLower1",
            "mapMountainUpper1",
            "mapRailroad1",
            "mapRailroadSE1",
            "mapTownCenter1",
            "mapTownCenter2"
        };

        protected static string[] mapBuildingInteriorFileNames = new string[] { //
            "buildingInterior0", // 9
            "buildingInteriorVet", // 10
            "buildingInteriorDoctor", // 11
            "", // 12
            "", // 13
            "", // 14
            "", // 15
            "", // 16
            "", // 17
            "", // 18
            "", // 19
            "", // 20
            "", // 21
            "", // 22
            ""  // 23
        };



        protected static int CurrentMap;
        public static int TILE_WIDTH = 32, TILE_HEIGHT = 32;

        protected static Map[] map;



        protected string[] underlayfileNames = new string[0];
        protected string[] overlayfileNames = new string[0];
        protected Color[] passibleGrid;

        protected int width, height;

        protected MapSwitcher[] switchers;

        public ArrayList items = new ArrayList();

        public Map(string[] underlayNames)
        {
            underlayfileNames = new string[underlayNames.Length];
            underlayfileNames = underlayNames;

            passibleGrid = new Color[SpriteSheet.GetSheet(underlayNames[0] + "passible").Width * SpriteSheet.GetSheet(underlayNames[0] + "passible").Height];
            SpriteSheet.GetSheet(underlayNames[0] + "passible").GetData<Color>(passibleGrid);
            width = SpriteSheet.GetSheet(underlayNames[0] + "passible").Width;
            height = SpriteSheet.GetSheet(underlayNames[0] + "passible").Height;
        }
        public Map(string[] underlayNames, string[] overlayNames)
        {
            underlayfileNames = new string[underlayNames.Length];
            underlayfileNames = underlayNames;

            overlayfileNames = new string[overlayNames.Length];
            overlayfileNames = overlayNames;

            passibleGrid = new Color[SpriteSheet.GetSheet(underlayNames[0] + "passible").Width * SpriteSheet.GetSheet(underlayNames[0] + "passible").Height];
            SpriteSheet.GetSheet(underlayNames[0] + "passible").GetData<Color>(passibleGrid);
            width = SpriteSheet.GetSheet(underlayNames[0] + "passible").Width;
            height = SpriteSheet.GetSheet(underlayNames[0] + "passible").Height;
        }

        public static void Init()
        {
            // create maps
            map = new Map[12];

            map[0] = new Map(new string[] { mapFileNames[0] });
            map[1] = new Map(new string[] { mapFileNames[1] });
            map[2] = new Map(new string[] { mapFileNames[2] }, new string[] { mapFileNames[2] + "Over" });
            map[3] = new Map(new string[] { mapFileNames[3], mapFileNames[9] }, new string[] { mapFileNames[3] + "Over", mapFileNames[9] + "Over" });
            map[4] = new Map(new string[] { mapFileNames[4], mapFileNames[10] });
            map[5] = new Map(new string[] { mapFileNames[5], mapFileNames[11] });
            map[6] = new Map(new string[] { mapFileNames[6] });
            map[7] = new Map(new string[] { mapFileNames[7], mapFileNames[12] });
            map[8] = new Map(new string[] { mapFileNames[8], mapFileNames[13], mapFileNames[14] }, new string[] { mapFileNames[8] + "Over", mapFileNames[13] + "Over", mapFileNames[14] + "Over" });
            
            map[9] = new BuildingInterior(new string[] { mapBuildingInteriorFileNames[0] },
                new Rectangle[] { new Rectangle(1, 3, 8, 7) },
                new Rectangle[] { new Rectangle(1, 1, 8, 2) });

            map[10] = new BuildingInterior(new string[] { mapBuildingInteriorFileNames[1] }, new string[] { mapBuildingInteriorFileNames[1] + "Over" },
                new Rectangle[] {
                    new Rectangle(0, 3, 7, 15),
                    new Rectangle(7, 3, 10, 10),
                    new Rectangle(17, 3, 7, 15),
                    new Rectangle(1, 18, 5, 5),
                    new Rectangle( 18, 18, 5, 5) },
                new Rectangle[] {
                    new Rectangle(1, 1, 5, 2),
                    new Rectangle(7, 1, 10, 2),
                    new Rectangle(18, 1, 5, 2),
                    new Rectangle(1, 16, 3, 2),
                    new Rectangle(5, 16, 1, 2),
                    new Rectangle(18, 16, 1, 2),
                    new Rectangle(20, 16, 3, 2) } );

            map[11] = new BuildingInterior(new string[] { mapBuildingInteriorFileNames[2] },
                new Rectangle[] {
                    new Rectangle(1, 1, 8, 4),
                    new Rectangle(21, 1, 10, 4) },
                new Rectangle[] {
                    new Rectangle(1, 5, 9, 10),
                    new Rectangle(20, 5, 12, 11) });

            // set starting map
            CurrentMap = 3;
            // set map switchers
            map[4].switchers = new MapSwitcher[8];
            map[4].switchers[0] = new MapSwitcher(new Rectangle(448, 3808 + 31, 96, 1), "4to3-0");
            map[4].switchers[1] = new MapSwitcher(new Rectangle(1088, 3808 + 31, 2016, 1), "4to3-1");
            map[4].switchers[2] = new MapSwitcher(new Rectangle(3456, 3808 + 31, 160, 1), "4to3-2");
            map[4].switchers[3] = new MapSwitcher(new Rectangle(4576 + 31, 3488, 1, 160), "4to5-0");
            map[4].switchers[4] = new MapSwitcher(new Rectangle(2848, 672, 32, 32), "4b-0");
            map[4].switchers[5] = new MapSwitcher(new Rectangle(2112, 2240, 32, 32), "4b-1");
            map[4].switchers[6] = new MapSwitcher(new Rectangle(0, 0, 0, 0), "");
            map[4].switchers[7] = new MapSwitcher(new Rectangle(0, 0, 0, 0), "");

            map[3].switchers = new MapSwitcher[13];
            map[3].switchers[0] = new MapSwitcher(new Rectangle(576, 0, 128, 1), "3to4-0");
            map[3].switchers[1] = new MapSwitcher(new Rectangle(1248, 0, 1952, 1), "3to4-1");
            map[3].switchers[2] = new MapSwitcher(new Rectangle(3584, 0, 160, 1), "3to4-2");
            map[3].switchers[3] = new MapSwitcher(new Rectangle(5088 + 31, 1120, 1, 160), "3to2-0");
            map[3].switchers[4] = new MapSwitcher(new Rectangle(5088 + 31, 3584, 1, 96), "3to2-1");
            map[3].switchers[5] = new MapSwitcher(new Rectangle(3808, 3808 + 31, 192, 1), "3to8-0");
            map[3].switchers[6] = new MapSwitcher(new Rectangle(3680, 1632, 32, 32), "3b-0");
            map[3].switchers[7] = new MapSwitcher(new Rectangle(608, 2016, 32, 32), "3b-1");
            map[3].switchers[8] = new MapSwitcher(new Rectangle(1920, 2112, 32, 32), "3b-2");
            map[3].switchers[9] = new MapSwitcher(new Rectangle(1664, 2720, 32, 32), "3b-3");
            map[3].switchers[10] = new MapSwitcher(new Rectangle(3840, 2528 + 31, 64, 1), "3b-4");
            map[3].switchers[11] = new MapSwitcher(new Rectangle(2464, 3456, 32, 32), "3b-5");
            map[3].switchers[12] = new MapSwitcher(new Rectangle(4352, 3520, 32, 32), "3b-6");

            map[8].switchers = new MapSwitcher[10];
            map[8].switchers[0] = new MapSwitcher(new Rectangle(3392, 0, 192, 1), "8to3-0");
            map[8].switchers[1] = new MapSwitcher(new Rectangle(6400, 0, 928, 1), "8to2-0");
            map[8].switchers[2] = new MapSwitcher(new Rectangle(8928 + 31, 1504, 1, 160), "8to0-0");
            map[8].switchers[3] = new MapSwitcher(new Rectangle(1760, 1248, 32, 32), "8b-0");
            map[8].switchers[4] = new MapSwitcher(new Rectangle(4352, 1248, 32, 32), "8b-1");
            map[8].switchers[5] = new MapSwitcher(new Rectangle(1920, 2400, 32, 32), "8b-2");
            map[8].switchers[6] = new MapSwitcher(new Rectangle(2752, 2432, 32, 32), "8b-3");
            map[8].switchers[7] = new MapSwitcher(new Rectangle(3488, 2336, 32, 32), "8b-4");
            map[8].switchers[8] = new MapSwitcher(new Rectangle(5024, 1888, 32, 32), "8b-5");
            map[8].switchers[9] = new MapSwitcher(new Rectangle(5696, 2080, 32, 32), "8b-6");

            map[0].switchers = new MapSwitcher[1];
            map[0].switchers[0] = new MapSwitcher(new Rectangle(0, 1504, 1, 160), "0to8-0");

            map[5].switchers = new MapSwitcher[3];
            map[5].switchers[0] = new MapSwitcher(new Rectangle(0, 1056, 1, 160), "5to4-0");
            map[5].switchers[1] = new MapSwitcher(new Rectangle(4192, 320 + 31, 128, 1), "5to6-0");
            map[5].switchers[2] = new MapSwitcher(new Rectangle(0, 0, 0, 0), "");

            map[1].switchers = new MapSwitcher[1];
            map[1].switchers[0] = new MapSwitcher(new Rectangle(160, 256 + 31, 160, 1), "1to7-0");

            map[6].switchers = new MapSwitcher[8];
            map[6].switchers[0] = new MapSwitcher(new Rectangle(864, 3040 + 31, 96, 1), "6to5-0");
            map[6].switchers[1] = new MapSwitcher(new Rectangle(2624, 928, 32, 32), "6b-0");
            map[6].switchers[2] = new MapSwitcher(new Rectangle(1312, 1568, 32, 32), "6b-1");
            map[6].switchers[3] = new MapSwitcher(new Rectangle(1792, 1664, 32, 32), "6b-2");
            map[6].switchers[4] = new MapSwitcher(new Rectangle(864, 2208, 32, 32), "6b-3");
            map[6].switchers[5] = new MapSwitcher(new Rectangle(2112, 2304, 32, 32), "6b-4");
            map[6].switchers[6] = new MapSwitcher(new Rectangle(2624, 2272, 32, 32), "6b-5");
            map[6].switchers[7] = new MapSwitcher(new Rectangle(0, 0, 0, 0), "");

            map[2].switchers = new MapSwitcher[7];
            map[2].switchers[0] = new MapSwitcher(new Rectangle(0, 864, 1, 160), "2to3-0");
            map[2].switchers[1] = new MapSwitcher(new Rectangle(0, 3328, 1, 96), "2to3-1");
            map[2].switchers[2] = new MapSwitcher(new Rectangle(1728, 3552 + 31, 960, 1), "2to8-0");
            map[2].switchers[3] = new MapSwitcher(new Rectangle(672, 384, 32, 32), "2b-0");
            map[2].switchers[4] = new MapSwitcher(new Rectangle(576, 896, 32, 32), "2b-1");
            map[2].switchers[5] = new MapSwitcher(new Rectangle(864, 896, 32, 32), "2b-2");
            map[2].switchers[6] = new MapSwitcher(new Rectangle(384, 3264, 32, 32), "2b-3");

            map[7].switchers = new MapSwitcher[2];
            map[7].switchers[0] = new MapSwitcher(new Rectangle(1312, 1120, 32, 32), "7b-0");
            map[7].switchers[1] = new MapSwitcher(new Rectangle(2880, 1504, 32, 32), "7b-1");

            map[9].switchers = new MapSwitcher[1];
            map[9].switchers[0] = new MapSwitcher(new Rectangle(160, 288, 32, 32), "exitto-");

            map[10].switchers = new MapSwitcher[1];
            map[10].switchers[0] = new MapSwitcher(new Rectangle(352, 736 + 31, 64, 1), "exitto-");

            map[11].switchers = new MapSwitcher[1];
            map[11].switchers[0] = new MapSwitcher(new Rectangle(864, 480 + 31, 64, 1), "exitto-");


            //////temp
            Furniture stool = new Furniture(new Vector2(5440 - 5440 % TILE_WIDTH, 1184 - 1184 % TILE_HEIGHT), "testStool0", 8);
            stool.playerInteractable = false;
            map[8].items.Add(stool);

            map[3].items.Add(new UpperClothing(new Vector2(2800, 300), "testUpperClothes0", 3));
            map[3].items.Add(new UpperClothing(new Vector2(2800, 350), "testUpperClothes1", 3));
            map[3].items.Add(new UpperClothing(new Vector2(2800, 400), "testUpperClothes2", 3));
            map[3].items.Add(new Crop(new Vector2(2800, 200), "apple", 3));
            map[3].items.Add(new Crop(new Vector2(2800, 230), "apple", 3));
            map[3].items.Add(new Crop(new Vector2(2800, 260), "apple", 3));
            map[3].items.Add(new Crop(new Vector2(2830, 200), "apple", 3));
            map[3].items.Add(new Crop(new Vector2(2830, 230), "apple", 3));
            map[3].items.Add(new Crop(new Vector2(2830, 260), "apple", 3));
            map[3].items.Add(new Crop(new Vector2(2860, 200), "apple", 3));
            map[3].items.Add(new Crop(new Vector2(2860, 230), "apple", 3));
            map[3].items.Add(new Crop(new Vector2(2860, 260), "apple", 3));
            map[3].items.Add(new Furniture(new Vector2(2900 - 2900 % TILE_WIDTH, 300 - 300 % TILE_HEIGHT), "testFurniture0", 3));
            Chest chest0 = new Chest(new Vector2(2900 - 2900 % TILE_WIDTH, 500 - 500 % TILE_HEIGHT), 3);

            map[3].items.Add(chest0);

            ((BuildingInterior)map[9]).setFlooring(new Flooring[] { new Flooring(new Vector2(0, 0), "testFlooring0", 9) });
            ((BuildingInterior)map[9]).setWallPaper(new WallPaper[] { new WallPaper(new Vector2(0, 0), "testWallPaper0", 9) });
            
            Furniture table = new Furniture(new Vector2(21 * 32 - 21 * 32 % TILE_WIDTH, 10 * 32 - 10 * 32 % TILE_HEIGHT), "testFurniture0", 11);
            table.playerInteractable = false;
            stool = new Furniture(new Vector2(23 * 32 - 23 * 32 % TILE_WIDTH, 9 * 32 - 9 * 32 % TILE_HEIGHT), "testStool0", 11);
            stool.playerInteractable = false;
            map[11].items.Add(table);
            map[11].items.Add(stool);

            ((BuildingInterior)map[10]).setFlooring(new Flooring[] {
                new Flooring(new Vector2(0, 0), "testFlooring0", 10),
                new Flooring(new Vector2(0, 0), "testFlooring0", 10),
                new Flooring(new Vector2(0, 0), "testFlooring0", 10),
                new Flooring(new Vector2(0, 0), "testFlooring0", 10),
                new Flooring(new Vector2(0, 0), "testFlooring0", 10) });
            ((BuildingInterior)map[10]).setWallPaper(new WallPaper[] {
                new WallPaper(new Vector2(0, 0), "testWallPaper0", 10),
                new WallPaper(new Vector2(0, 0), "testWallPaper0", 10),
                new WallPaper(new Vector2(0, 0), "testWallPaper0", 10),
                new WallPaper(new Vector2(0, 0), "testWallPaper0", 10),
                new WallPaper(new Vector2(0, 0), "testWallPaper0", 10),
                new WallPaper(new Vector2(0, 0), "testWallPaper0", 10),
                new WallPaper(new Vector2(0, 0), "testWallPaper0", 10) });

            ((BuildingInterior)map[11]).setFlooring(new Flooring[] {
                new Flooring(new Vector2(0, 0), "testFlooring0", 10),
                new Flooring(new Vector2(0, 0), "testFlooring0", 11) });
            ((BuildingInterior)map[11]).setWallPaper(new WallPaper[] {
                new WallPaper(new Vector2(0, 0), "testWallPaper0", 10),
                new WallPaper(new Vector2(0, 0), "testWallPaper0", 11) });
        }

        public static void Update(GameTime gameTime)
        {
            CurrentMap = Player.player.getAtMap();
            foreach (Item i in map[CurrentMap].items)
            {
                i.update(gameTime);
            }
        }

        public static void Draw(SpriteBatch spriteBatch)
        {
            CurrentMap = Player.player.getAtMap();

            // wallpaper flooring
            if (map[CurrentMap].GetType().Name == "BuildingInterior")
            {
                // wallpaper
                WallPaper[] wTemp = ((BuildingInterior)map[CurrentMap]).getWallPaper();
                Rectangle[] wP = ((BuildingInterior)map[CurrentMap]).getWallPaperArea();
                for (int w = 0; w < wP.Length; w++)
                {
                    wTemp[w].draw(spriteBatch, wP[w]);
                }
                // flooring
                Flooring[] fTemp = ((BuildingInterior)map[CurrentMap]).getFlooring();
                Rectangle[] fP = ((BuildingInterior)map[CurrentMap]).getFlooringArea();
                for (int f = 0; f < fP.Length; f++)
                {
                    fTemp[f].draw(spriteBatch, fP[f]);
                }
            }

            // underlay
            for (int i = 0; i < map[CurrentMap].underlayfileNames.Length; i++)
            {
                Camera.Draw(spriteBatch, SpriteSheet.GetSheet(map[CurrentMap].underlayfileNames[i]), new Vector2(0 + 4000f * i, 0), //
                    new Rectangle(0, 0, SpriteSheet.GetSheet(map[CurrentMap].underlayfileNames[i]).Width, SpriteSheet.GetSheet(map[CurrentMap].underlayfileNames[i]).Height), Color.White, 0f, 1.0f, false, 0.0000005f, 1.0f);
            }

            // overlay
            for (int i = 0; i < map[CurrentMap].overlayfileNames.Length; i++)
            {
                Camera.Draw(spriteBatch, SpriteSheet.GetSheet(map[CurrentMap].overlayfileNames[i]), new Vector2(0 + 4000f * i, 0), //
                    new Rectangle(0, 0, SpriteSheet.GetSheet(map[CurrentMap].overlayfileNames[i]).Width, SpriteSheet.GetSheet(map[CurrentMap].overlayfileNames[i]).Height), Color.White, 0f, 1.0f, false, 0.9999985f, 1.0f);
            }

            foreach (Item i in map[CurrentMap].items)
            {
                i.draw(spriteBatch);
            }
        }

        public static Color[] GetPassibleGrid(int i)
        {
            return map[i].passibleGrid;
        }

        public static Color GetPassibleGrid(int i, int x, int y)
        {
            return map[i].passibleGrid[x + y * Width(i)];
        }

        public static int Width(int i)
        {
            // temp
            return map[i].width;
        }

        public static int Height(int i)
        {
            // temp
            return map[i].height;
        }

        public static void SwitchMap(int i)
        {
            CurrentMap = i;
        }

        public static MapSwitcher[] GetSwitchers(int i)
        {
            return map[i].switchers;
        }

        public static ArrayList GetItems(int i)
        {
            return map[i].items;
        }

        public static Item RemoveItem(int i, Vector2 clickPos)
        {
            for (int index = 0; index < map[i].items.Count; index++)
            {
                if (((Item)map[i].items[index]).getCollideRect().Contains(clickPos))
                {
                    Item temp = (Item)map[i].items[index];
                    map[i].items.RemoveAt(index);
                    return temp;
                }
            }
            return null;
        }

        public static Item RemoveItem(int i, Rectangle getBox)
        {
            for (int index = 0; index < map[i].items.Count; index++)
            {
                if (((Item)map[i].items[index]).getCollideRect().Intersects(getBox))
                {
                    Item temp = (Item)map[i].items[index];
                    map[i].items.RemoveAt(index);
                    return temp;
                }
            }
            return null;
        }

        public static bool AddItem(int i, Item it)
        {
            map[i].items.Add(it);
            it.setAtMap(i);
            return true;
        }

        public static bool collideWithMap(int i, Rectangle r, bool collideWithSmallItems)
        {
            if (r.X < 0 || r.Y < 0 || r.X + r.Width > Map.Width(i) * Map.TILE_WIDTH || r.Y + r.Height > Map.Height(i) * Map.TILE_HEIGHT) return true;
            // collide items
            foreach (Item it in map[CurrentMap].items)
            {
                if (r.Intersects(it.getCollideRect())) {
                    if (!it.isSmallItem())
                    {
                        return true;
                    }
                    else if (collideWithSmallItems && it.isSmallItem())
                    {
                        return true;
                    }
                }
            }

            // collide tiles
            for (int x = r.X; x < map[i].width * TILE_WIDTH && x < (r.X + r.Width); x++)
            {
                for (int y = r.Y; y < map[i].height * TILE_HEIGHT && y < (r.Y + r.Height); y++)
                {
                    Color c = map[i].passibleGrid[x / TILE_WIDTH + y / TILE_HEIGHT * map[i].width];
                    if (c.R == 0 && c.G == 0 && c.B == 0)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        public static Map getMap(int i)
        {
            return map[i];
        }
    }
}