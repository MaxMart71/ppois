class Base:
    def __init__(self, place: str, type: str, amount_of_soldiers: int, amount_of_technique: int):
        self.__place = place
        self.__type = type
        self.__amount_of_soldiers = amount_of_soldiers
        self.__amount_of_technique = amount_of_technique

    @property
    def place(self) -> str:
        return self.__place

    @place.setter
    def place(self, place: str):
        self.__place = place

    @property
    def type(self) -> str:
        return self.__type

    @type.setter
    def type(self, type: str):
        self.__type = type

    @property
    def amount_of_soldiers(self) -> int:
        return self.__amount_of_soldiers

    @amount_of_soldiers.setter
    def amount_of_soldiers(self, amount_of_soldiers: int):
        self.__amount_of_soldiers = amount_of_soldiers

    @property
    def amount_of_technique(self) -> int:
        return self.__amount_of_technique

    @amount_of_technique.setter
    def amount_of_technique(self, amount_of_technique: int):
        self.__amount_of_technique = amount_of_technique
