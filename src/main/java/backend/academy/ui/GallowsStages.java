package backend.academy.ui;

import lombok.Getter;

@Getter
public enum GallowsStages {
    STAGE_0("""
         ╓─────╖
         │
         │
         │
         │
        ═╧══"""),

    STAGE_1("""
         ╓─────╖
         │     ┊
         │
         │
         │
        ═╧══"""),

    STAGE_2("""
         ╓─────╖
         │     ┊
         │     O
         │
         │
        ═╧══"""),

    STAGE_3("""
         ╓─────╖
         │     ┊
         │     O
         │     |
         │
        ═╧══"""),

    STAGE_4("""
         ╓─────╖
         │     ┊
         │     O
         │    ╱|
         │
        ═╧══"""),

    STAGE_5("""
         ╓─────╖
         │     ┊
         │     O
         │    ╱|╲
         │
        ═╧══"""),

    STAGE_6("""
         ╓─────╖
         │     ┊
         │     O
         │    ╱|╲
         │    ╱
        ═╧══"""),

    STAGE_7("""
         ╓─────╖
         │     ┊
         │     O
         │    ╱|╲
         │    ╱ ╲
        ═╧══""");

    private final String stage;

    GallowsStages(String stage) {
        this.stage = stage;
    }
}
