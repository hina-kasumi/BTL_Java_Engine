package com.hina.constant;

public class HeroConst {
    public static final String HERO_TEXTURE_SRC = "assets/textures/Character/";
    public static final String HERO_TEXTURE_SRC_PNG = "/png/";

    public static class Crystal extends HeroConst {
        public static String SRC = getSrc(Crystal.class.getSimpleName());
        public static float ATTACK_BOX_BASE_ATTACK_WIDTH = 1f;
        public static float ATTACK_BOX_BASE_ATTACK_HEIGHT = 1f;
        public static float HERO_MAX_HEALTH = 100f;
        public static float OFFSET_Y = 1;
    }

    public static class Fire extends HeroConst {
        public static String SRC = getSrc(Fire.class.getSimpleName());
        public static float ATTACK_BOX_BASE_ATTACK_WIDTH = 2f;
        public static float ATTACK_BOX_BASE_ATTACK_HEIGHT = 2f;
        public static float HERO_MAX_HEALTH = 100f;
        public static float OFFSET_Y = 1;
    }

    public static class Ground extends HeroConst {
        public static String SRC = getSrc(Ground.class.getSimpleName());
        public static float ATTACK_BOX_BASE_ATTACK_WIDTH = 1f;
        public static float ATTACK_BOX_BASE_ATTACK_HEIGHT = 1f;
        public static float HERO_MAX_HEALTH = 100f;
        public static float OFFSET_Y = 7;
    }

    public static class Leaf extends HeroConst {
        public static String SRC = getSrc(Leaf.class.getSimpleName());
        public static float ATTACK_BOX_BASE_ATTACK_WIDTH = 1f;
        public static float ATTACK_BOX_BASE_ATTACK_HEIGHT = 1f;
        public static float HERO_MAX_HEALTH = 100f;
        public static float OFFSET_Y = 1;
    }

    public static class Metal extends HeroConst {
        public static String SRC = getSrc(Metal.class.getSimpleName());
        public static float ATTACK_BOX_BASE_ATTACK_WIDTH = 1f;
        public static float ATTACK_BOX_BASE_ATTACK_HEIGHT = 1f;
        public static float HERO_MAX_HEALTH = 100f;
        public static float OFFSET_Y = 1;
    }

    public static class Water extends HeroConst {
        public static String SRC = getSrc(Water.class.getSimpleName());
        public static float ATTACK_BOX_BASE_ATTACK_WIDTH = 1f;
        public static float ATTACK_BOX_BASE_ATTACK_HEIGHT = 1f;
        public static float HERO_MAX_HEALTH = 100f;
        public static float OFFSET_Y = 1;
    }

    public static class Wind extends HeroConst {
        public static String SRC = getSrc(Wind.class.getSimpleName());
        public static float ATTACK_BOX_BASE_ATTACK_WIDTH = 1f;
        public static float ATTACK_BOX_BASE_ATTACK_HEIGHT = 1f;
        public static float HERO_MAX_HEALTH = 100f;
        public static float OFFSET_Y = 1;
    }

    public static String getSrc(String string) {
        return HERO_TEXTURE_SRC + string + HERO_TEXTURE_SRC_PNG;
    }
}
