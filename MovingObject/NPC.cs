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
    class NPC : Character
    {
        // temp
        int length = 500;

        public NPC(string n)
        {

            name = n;
            className = "NPC";
        }

        public override void update(GameTime gameTime)
        {
        }

        public override void move()
        {
        }

    }
}
