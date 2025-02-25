package com.hina.constant;


public class HeroConst {
    public static final String HERO_TEXTURE_SRC = "assets/textures/Character/";
    public static final String HERO_TEXTURE_SRC_PNG = "/png/";
    public static final String HERO_IMAGE = "textures/Character/";

    public static class Crystal extends HeroConst {
        public static final String SRC = getSrc(Crystal.class.getSimpleName());
        public static final String HERO_IMAGE_SRC = HERO_IMAGE + "Crystal/crystal_mauler.png";
        public static final int BASIC_ATTACK_START_AT = 4;
        public static final int BASIC_ATTACK_END_AT = 7;
        public static final float ATTACK_BOX_BASE_ATTACK_WIDTH = 1f;
        public static final float ATTACK_BOX_BASE_ATTACK_HEIGHT = 1f;
        public static final float HERO_MAX_HEALTH = 100f;
        public static final float OFFSET_Y = 1;
        public static final float SPECIAL_ATTACK_WIDTH = 3.5f;
        public static final float SPECIAL_ATTACK_HEIGHT = 2f;
        public static final int SPECIAL_ATTACK_START_FRAME = 9;
        public static final int SPECIAL_ATTACK_END_FRAME = 12;
        public static final float SPECIAL_ATTACK_DAMAGE = 30f;
        public static final float SPECIAL_ATTACK_OFFSET_X = SPECIAL_ATTACK_WIDTH;
        public static final int DEFEND_AT = 2;
    }

    public static class Fire extends HeroConst {
        public static final String SRC = getSrc(Fire.class.getSimpleName());
        public static final String HERO_IMAGE_SRC = HERO_IMAGE + "Fire/fire_knight.png";
        public static final int BASIC_ATTACK_START_AT = 5;
        public static final int BASIC_ATTACK_END_AT = 8;
        public static final float ATTACK_BOX_BASE_ATTACK_WIDTH = 2f;
        public static final float ATTACK_BOX_BASE_ATTACK_HEIGHT = 2f;
        public static final float HERO_MAX_HEALTH = 100f;
        public static final float OFFSET_Y = 1;
        public static final float SPECIAL_ATTACK_WIDTH = 2.5f;
        public static final float SPECIAL_ATTACK_HEIGHT = 2f;
        public static final int SPECIAL_ATTACK_START_FRAME = 13;
        public static final int SPECIAL_ATTACK_END_FRAME = 15;
        public static final float SPECIAL_ATTACK_DAMAGE = 30f;
        public static final float SPECIAL_ATTACK_OFFSET_X = SPECIAL_ATTACK_WIDTH;
        public static final int DEFEND_AT = 3;
    }

    public static class Ground extends HeroConst {
        public static final String SRC = getSrc(Ground.class.getSimpleName());
        public static final String HERO_IMAGE_SRC = HERO_IMAGE + "Ground/ground_monk.png";
        public static final int BASIC_ATTACK_START_AT = 2;
        public static final int BASIC_ATTACK_END_AT = 4;
        public static final float ATTACK_BOX_BASE_ATTACK_WIDTH = 1f;
        public static final float ATTACK_BOX_BASE_ATTACK_HEIGHT = 1f;
        public static final float HERO_MAX_HEALTH = 100f;
        public static final float OFFSET_Y = 7;
        public static final float SPECIAL_ATTACK_WIDTH = 2;
        public static final float SPECIAL_ATTACK_HEIGHT = 2;
        public static final float SPECIAL_ATTACK_DAMAGE = 20f;
        public static final int[] SPECIAL_ATTACK_TURNS = new int[]{6, 9, 11, 19};
        public static final float SPECIAL_ATTACK_OFFSET_X = SPECIAL_ATTACK_WIDTH;
        public static final int DEFEND_AT = 4;
    }

    public static class Leaf extends HeroConst {
        public static final String SRC = getSrc(Leaf.class.getSimpleName());
        public static final String HERO_IMAGE_SRC = HERO_IMAGE + "Leaf/leaf_ranger.png";
        public static final int BASIC_ATTACK_START_AT = 5;
        public static final int BASIC_ATTACK_END_AT = 7;
        public static final float ATTACK_BOX_BASE_ATTACK_WIDTH = 2f;
        public static final float ATTACK_BOX_BASE_ATTACK_HEIGHT = 1f;
        public static final float HERO_MAX_HEALTH = 100f;
        public static final float OFFSET_Y = 1;
        public static final float SPECIAL_ATTACK_WIDTH = 3.6f;
        public static final float SPECIAL_ATTACK_HEIGHT = 1f;
        public static final int SPECIAL_ATTACK_START_FRAME = 10;
        public static final int SPECIAL_ATTACK_END_FRAME = 12;
        public static final float SPECIAL_ATTACK_DAMAGE = 30f;
        public static final float SPECIAL_ATTACK_OFFSET_X = SPECIAL_ATTACK_WIDTH;
        public static final int DEFEND_AT = 8;
    }

    public static class Metal extends HeroConst {
        public static final String SRC = getSrc(Metal.class.getSimpleName());
        public static final String HERO_IMAGE_SRC = HERO_IMAGE + "Metal/metal_bladekeeper.png";
        public static final int BASIC_ATTACK_START_AT = 2;
        public static final int BASIC_ATTACK_END_AT = 3;
        public static final float ATTACK_BOX_BASE_ATTACK_WIDTH = 1.2f;
        public static final float ATTACK_BOX_BASE_ATTACK_HEIGHT = 1f;
        public static final float HERO_MAX_HEALTH = 100f;
        public static final float OFFSET_Y = 1;
        public static final float SPECIAL_ATTACK_WIDTH = 4.5f;
        public static final float SPECIAL_ATTACK_HEIGHT = 1.4f;
        public static final int SPECIAL_ATTACK_START_FRAME = 5;
        public static final int SPECIAL_ATTACK_END_FRAME = 8;
        public static final float SPECIAL_ATTACK_DAMAGE = 30f;
        public static final float SPECIAL_ATTACK_OFFSET_X = 0.5f;
        public static final int DEFEND_AT = 4;
    }

    public static class Water extends HeroConst {
        public static final String SRC = getSrc(Water.class.getSimpleName());
        public static final String HERO_IMAGE_SRC = HERO_IMAGE + "Water/water_priestess.png";
        public static final int BASIC_ATTACK_START_AT = 3;
        public static final int BASIC_ATTACK_END_AT = 4;
        public static final float ATTACK_BOX_BASE_ATTACK_WIDTH = 1.5f;
        public static final float ATTACK_BOX_BASE_ATTACK_HEIGHT = 1f;
        public static final float HERO_MAX_HEALTH = 100f;
        public static final float OFFSET_Y = 1;
        public static final float SPECIAL_ATTACK_WIDTH = 2.5f;
        public static final float SPECIAL_ATTACK_HEIGHT = 2;
        public static final float SPECIAL_ATTACK_DAMAGE = 20f;
        public static final int[] SPECIAL_ATTACK_TURNS = new int[]{14, 23};
        public static final float SPECIAL_ATTACK_OFFSET_X = SPECIAL_ATTACK_WIDTH;
        public static final int DEFEND_AT = 3;
    }

    public static class Wind extends HeroConst {
        public static final String SRC = getSrc(Wind.class.getSimpleName());
        public static final String HERO_IMAGE_SRC = HERO_IMAGE + "Wind/wind_hashashin.png";
        public static final int BASIC_ATTACK_START_AT = 3;
        public static final int BASIC_ATTACK_END_AT = 4;
        public static final float ATTACK_BOX_BASE_ATTACK_WIDTH = 1f;
        public static final float ATTACK_BOX_BASE_ATTACK_HEIGHT = 1f;
        public static final float HERO_MAX_HEALTH = 100f;
        public static final float OFFSET_Y = 1;
        public static final float SPECIAL_ATTACK_WIDTH = 3;
        public static final float SPECIAL_ATTACK_HEIGHT = 2;
        public static final float SPECIAL_ATTACK_DAMAGE = 30f;
        public static final int[] SPECIAL_ATTACK_TURNS = new int[]{12, 18, 20};
        public static final float SPECIAL_ATTACK_OFFSET_X = 0f;
        public static final int DEFEND_AT = 2;
    }

    public static String getSrc(String string) {
        return HERO_TEXTURE_SRC + string + HERO_TEXTURE_SRC_PNG;
    }
}
