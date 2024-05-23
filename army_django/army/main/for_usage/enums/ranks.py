import enum


@enum.unique
class Rank(enum.Enum):
    level1 = "lvl1"
    level2 = "lvl2"
    level3 = "lvl3"
    level4 = "lvl4"
    level5 = "lvl5"


print(isinstance(Rank.level1, Rank))
