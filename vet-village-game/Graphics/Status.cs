using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using System.Collections.Generic;

namespace vet
{
    class Status
    {
        public static int size = 16;

        public string name;
        private Animation[] anims;
        public bool show = true;
        public bool constant = false;
        public int duration = 0;
        private int animsSpeed = 20;

        public int currentAnim = 0;

        public Status(string n, bool b)
        {
            name = n;
            show = b;
            anims = new Animation[1];
            switch (name)
            {
                case "heart":
                    anims[0] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(0 * size, 0 * size, size, size) }, animsSpeed);
                    break;
                case "happyFace":
                    anims[0] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(1 * size, 0 * size, size, size) }, animsSpeed);
                    break;
                case "sadFace":
                    anims[0] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(2 * size, 0 * size, size, size) }, animsSpeed);
                    break;
                case "angry":
                    anims[0] = new Animation("statusIcon", 4, new Rectangle[] { new Rectangle(3 * size, 0 * size, size, size), //
                                                                               new Rectangle(4 * size, 0 * size, size, size), //
                                                                               new Rectangle(5 * size, 0 * size, size, size), //
                                                                               new Rectangle(4 * size, 0 * size, size, size) }, animsSpeed);
                    break;
                case "sick":
                    constant = true;
                    anims[0] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(0 * size, 4 * size, size, size) }, animsSpeed);
                    break;
                case "vomit":
                    constant = true;
                    anims[0] = new Animation("statusIcon", 4, new Rectangle[] { new Rectangle(1 * size, 4 * size, size, size), //
                                                                               new Rectangle(2 * size, 4 * size, size, size), //
                                                                               new Rectangle(3 * size, 4 * size, size, size), //
                                                                               new Rectangle(4 * size, 4 * size, size, size) }, animsSpeed);
                    break;
                case "death":
                    constant = true;
                    anims[0] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(5 * size, 4 * size, size, size) }, animsSpeed);
                    break;
                case "hp":
                    anims = new Animation[6];
                    anims[0] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(0 * size, 5 * size, size, size) }, animsSpeed);
                    anims[1] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(1 * size, 5 * size, size, size) }, animsSpeed);
                    anims[2] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(2 * size, 5 * size, size, size) }, animsSpeed);
                    anims[3] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(3 * size, 5 * size, size, size) }, animsSpeed);
                    anims[4] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(4 * size, 5 * size, size, size) }, animsSpeed);
                    anims[5] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(5 * size, 5 * size, size, size) }, animsSpeed);
                    break;
                case "hunger":
                    anims = new Animation[3];
                    anims[0] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(0 * size, 1 * size, size, size) }, animsSpeed);
                    anims[1] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(1 * size, 1 * size, size, size) }, animsSpeed);
                    anims[2] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(2 * size, 1 * size, size, size) }, animsSpeed);
                    break;
                case "sleep":
                    constant = true;
                    anims[0] = new Animation("statusIcon", 3, new Rectangle[] { new Rectangle(0 * size, 2 * size, size, size), //
                                                                                  new Rectangle(1 * size, 2 * size, size, size), //
                                                                                  new Rectangle(2 * size, 2 * size, size, size) }, animsSpeed);
                    break;
                case "question":
                    anims[0] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(0 * size, 3 * size, size, size) }, animsSpeed);
                    break;
                case "exclamation":
                    anims[0] = new Animation("statusIcon", 1, new Rectangle[] { new Rectangle(1 * size, 3 * size, size, size) }, animsSpeed);
                    break;
                case "movingHearts":
                    constant = true;
                    duration = 1;
                    animsSpeed /= 3;
                    anims[0] = new Animation("statusIcon", 4, new Rectangle[] { new Rectangle(0 * size, 6 * size, size, size), //
                                                                                  new Rectangle(1 * size, 6 * size, size, size), //
                                                                                  new Rectangle(2 * size, 6 * size, size, size), //
                                                                                  new Rectangle(3 * size, 6 * size, size, size) }, animsSpeed);
                    break;
                default:
                    break;
            }
        }

        public static void Update(GameTime gameTime, Dictionary<string, Status> statuses, Character c)
        {
            foreach (KeyValuePair<string, Status> s in statuses)
            {
                // set based off character stats
                switch (s.Value.name) {
                    case "movingHearts":
                        if ((c.hunger / c.hungerMax) >= 0.60 && //
                            (c.familiarity / c.familiarityMax) >= 0.95 && //
                            (c.happiness / c.happinessMax) >= 0.95 && //
                            (c.sickness / c.sicknessMax) <= 0.0 && //
                            (c.energy / c.energyMax) >= 0.2 && //
                            (c.hp / c.hpMax) >= 0.8)
                        {
                            s.Value.duration = 300;
                            s.Value.constant = true;
                        }
                        else
                        {
                            s.Value.constant = false;
                        }
                        break;
                    case "hp":
                        if ((c.hp / c.hpMax) <= 1.0 && (c.hp / c.hpMax) > 0.9) s.Value.currentAnim = 0;
                        if ((c.hp / c.hpMax) <= 0.9 && (c.hp / c.hpMax) > 0.7) s.Value.currentAnim = 1;
                        if ((c.hp / c.hpMax) <= 0.7 && (c.hp / c.hpMax) > 0.5) s.Value.currentAnim = 2;
                        if ((c.hp / c.hpMax) <= 0.5 && (c.hp / c.hpMax) > 0.3) s.Value.currentAnim = 3;
                        if ((c.hp / c.hpMax) <= 0.3 && (c.hp / c.hpMax) > 0.1) s.Value.currentAnim = 4;
                        if ((c.hp / c.hpMax) <= 0.1 && (c.hp / c.hpMax) >= 0.0) s.Value.currentAnim = 5;
                        if (s.Value.currentAnim == 0)
                        {
                            s.Value.constant = false;
                        }
                        else if (s.Value.currentAnim != 0)
                        {
                            s.Value.duration = 300;
                            s.Value.constant = true;
                        }
                        break;
                    case "hunger":
                        if ((c.hunger / c.hungerMax) <= 1.0 && (c.hunger / c.hungerMax) > 0.8) s.Value.currentAnim = 0;
                        if ((c.hunger / c.hungerMax) <= 0.8 && (c.hunger / c.hungerMax) > 0.2) s.Value.currentAnim = 1;
                        if ((c.hunger / c.hungerMax) <= 0.2 && (c.hunger / c.hungerMax) >= 0.0) s.Value.currentAnim = 2;
                        if (s.Value.currentAnim == 0)
                        {
                            s.Value.constant = false;
                        }
                        else if (s.Value.currentAnim != 0)
                        {
                            s.Value.duration = 300;
                            s.Value.constant = true;
                        }
                        break;
                    default:
                        break;
                }

                // anim updates
                foreach (Animation a in s.Value.anims)
                {
                    a.update(gameTime);
                }

                // duration updates
                if (!s.Value.constant)
                {
                    s.Value.duration--;
                    if (s.Value.duration < 0) s.Value.duration = 0;
                }
                s.Value.show = s.Value.duration <= 0 ? false : true;
            }
        }

        public static void Draw(SpriteBatch spriteBatch, Dictionary<string, Status> statuses, Vector2 pos)
        {
            int count = 0;
            foreach (KeyValuePair<string, Status> s in statuses)
            {
                if (s.Value.duration > 0 && s.Value.show)
                {
                    count++;
                }
            }
            pos.X -= (count * size + count * 2 - 2) / 2;
            pos.Y -= (size + 2);
            int index = 0;
            foreach (KeyValuePair<string, Status> s in statuses)
            {
                if (s.Value.duration > 0 && s.Value.show)
                {
                    s.Value.anims[s.Value.currentAnim].draw(spriteBatch, pos, 0.0f, 0.9999980f);
                    index++;
                    pos.X += size + 2;
                }
            }
        }


    }
}