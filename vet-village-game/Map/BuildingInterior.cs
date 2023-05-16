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
    class BuildingInterior : Map
    {

        protected Flooring[] flooring;
        protected WallPaper[] wallPaper;
        protected Rectangle[] flooringArea;
        protected Rectangle[] wallPaperArea;

        public BuildingInterior(string[] underlayNames, Rectangle[] f, Rectangle[] w) : base(underlayNames)
        {
            flooringArea = f;
            wallPaperArea = w;
        }
        public BuildingInterior(string[] underlayNames, string[] overlayNames, Rectangle[] f, Rectangle[] w) : base(underlayNames, overlayNames)
        {
            flooringArea = f;
            wallPaperArea = w;
        }

        public Flooring[] getFlooring()
        {
            return flooring;
        }

        public WallPaper[] getWallPaper()
        {
            return wallPaper;
        }

        public void setFlooring(Flooring[] f)
        {
            flooring = f;
        }

        public void setWallPaper(WallPaper[] w)
        {
            wallPaper = w;
        }

        public Rectangle[] getFlooringArea()
        {
            return flooringArea;
        }

        public Rectangle[] getWallPaperArea()
        {
            return wallPaperArea;
        }
    }
}