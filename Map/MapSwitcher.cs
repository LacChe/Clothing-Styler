using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace vet
{
    class MapSwitcher
    {

        private Rectangle rect;
        private string name;

        public MapSwitcher(Rectangle r, string n)
        {
            rect = r;
            name = n;
        }

        public Rectangle getRect()
        {
            return rect;
        }

        public void action(MovingObject m) // currently only for static player
        {
            int newX = 0, newY = 0, newAtMap = 0;
            switch (name)
            {
                // map4
                case "4to3-0":
                    newAtMap = 3;
                    newX = (int)m.getstandPos().X + 160;
                    newY = 2;
                    break;
                case "4to3-1":
                    newAtMap = 3;
                    newX = (int)m.getstandPos().X + 160;
                    newY = 2;
                    break;
                case "4to3-2":
                    newAtMap = 3;
                    newX = (int)m.getstandPos().X + 160;
                    newY = 2;
                    break;
                case "4to5-0":
                    newAtMap = 5;
                    newX = 2;
                    newY = (int)m.getstandPos().Y - 2432;
                    break;
                case "4b-0":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "4b-1":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;

                // map4
                case "3to4-0":
                    newAtMap = 4;
                    newX = (int)m.getstandPos().X - 160;
                    newY = (Map.Height(newAtMap) - 1) * Map.TILE_WIDTH - 1;
                    break;
                case "3to4-1":
                    newAtMap = 4;
                    newX = (int)m.getstandPos().X - 160;
                    newY = (Map.Height(newAtMap) - 1) * Map.TILE_WIDTH - 1;
                    break;
                case "3to4-2":
                    newAtMap = 4;
                    newX = (int)m.getstandPos().X - 160;
                    newY = (Map.Height(newAtMap) - 1) * Map.TILE_WIDTH - 1;
                    break;
                case "3to2-0":
                    newAtMap = 2;
                    newX = 2;
                    newY = (int)m.getstandPos().Y - 256;
                    break;
                case "3to2-1":
                    newAtMap = 2;
                    newX = 2;
                    newY = (int)m.getstandPos().Y - 256;
                    break;
                case "3to8-0":
                    newAtMap = 8;
                    newX = (int)m.getstandPos().X - 416;
                    newY = 2;
                    break;
                case "3b-0":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "3b-1":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 11;
                    newX = 896;
                    newY = 480;
                    break;
                case "3b-2":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "3b-3":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "3b-4":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 10;
                    newX = 370;
                    newY = 715;
                    break;
                case "3b-5":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "3b-6":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;

                // map8
                case "8to3-0":
                    newAtMap = 3;
                    newX = (int)m.getstandPos().X + 416;
                    newY = (Map.Height(newAtMap) - 1) * Map.TILE_WIDTH - 1;
                    break;
                case "8to2-0":
                    newAtMap = 2;
                    newX = (int)m.getstandPos().X - 4672;
                    newY = (Map.Height(newAtMap) - 1) * Map.TILE_WIDTH - 1;
                    break;
                case "8to0-0":
                    newAtMap = 0;
                    newX = 2;
                    newY = (int)m.getstandPos().Y;
                    break;
                case "8b-0":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "8b-1":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "8b-2":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "8b-3":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "8b-4":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "8b-5":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "8b-6":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;

                // map0
                case "0to8-0":
                    newAtMap = 8;
                    newX = (Map.Width(newAtMap) - 1) * Map.TILE_WIDTH - 1;
                    newY = (int)m.getstandPos().Y;
                    break;

                // map5
                case "5to4-0":
                    newAtMap = 4;
                    newX = (Map.Width(newAtMap) - 1) * Map.TILE_WIDTH - 1;
                    newY = (int)m.getstandPos().Y + 2432;
                    break;
                case "5to6-0":
                    newAtMap = 6;
                    newX = (int)m.getstandPos().X - 3328;
                    newY = (Map.Height(newAtMap) - 1) * Map.TILE_WIDTH - 1;
                    break;

                // map6
                case "6to5-0":
                    newAtMap = 5;
                    newX = (int)m.getstandPos().X + 3328;
                    newY = 320 + 2;
                    break;
                case "6b-0":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "6b-1":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "6b-2":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "6b-3":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "6b-4":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "6b-5":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;

                // map2
                case "2to3-0":
                    newAtMap = 3;
                    newX = (Map.Width(newAtMap) - 1) * Map.TILE_WIDTH - 1;
                    newY = (int)m.getstandPos().Y + 256;
                    break;
                case "2to3-1":
                    newAtMap = 3;
                    newX = (Map.Width(newAtMap) - 1) * Map.TILE_WIDTH - 1;
                    newY = (int)m.getstandPos().Y + 256;
                    break;
                case "2to8-0":
                    newAtMap = 8;
                    newX = (int)m.getstandPos().X + 4672;
                    newY = 2;
                    break;
                case "2b-0":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "2b-1":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "2b-2":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;

                // map7
                case "7b-0":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;
                case "7b-1":
                    // temp
                    m.lastMap = m.getAtMap();
                    m.lastMapOffest = m.getstandPos();
                    newAtMap = 9;
                    newX = 160;
                    newY = 256;
                    break;

                // map9
                case "exitto-":
                    // temp
                    newAtMap = m.lastMap;
                    newX = (int)m.lastMapOffest.X;
                    newY = (int)m.lastMapOffest.Y + Map.TILE_HEIGHT * 1;
                    break;


                default:
                    break;

            }

            // set values
            m.setAtMap(newAtMap);
            m.setstandPos(new Vector2(newX, newY));
        }
    }
}