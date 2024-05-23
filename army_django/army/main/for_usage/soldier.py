from main.for_usage.enums.ranks import Rank


class Man():
    def __init__(self, first_name: str, last_name: str, is_fit: bool, age: int):
        self.__first_name = first_name
        self.__last_name = last_name
        self.__age = age
        self.__is_fit = is_fit

    @property
    def first_name(self) -> str:
        return self.__first_name

    @first_name.setter
    def first_name(self, first_name: str):
        self.__first_name = first_name

    @property
    def last_name(self) -> str:
        return self.__last_name

    @last_name.setter
    def last_name(self, last_name: str):
        self.__last_name = last_name

    @property
    def age(self) -> int:
        return self.__age

    @age.setter
    def age(self, age: int):
        self.__age = age

    @property
    def is_fit(self) -> bool:
        return self.__is_fit

    @is_fit.setter
    def is_fit(self, is_fit: bool):
        self.__is_fit = is_fit


class Soldier(Man):

    def __init__(self, first_name: str, last_name: str, is_fit: bool, age: int,  rank=Rank.level1, is_learned=False):
        super().__init__(first_name, last_name, is_fit, age)
        self.__is_learned = is_learned
        self.__rank = rank

    @property
    def rank(self) -> Rank:
        return self.__rank

    @rank.setter
    def rank(self, rank: Rank):
        self.__rank = rank

    @property
    def is_learned(self) -> bool:
        return self.__is_learned

    @is_learned.setter
    def is_learned(self, is_learned: bool):
        self.__is_learned = is_learned
