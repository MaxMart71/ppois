
class Technique:

    def __init__(self, type: str, model: str, amount: int):
        self.__type = type
        self.__model = model
        self.__amount = amount

    @property
    def type(self) -> str:
        return self.__type

    @type.setter
    def type(self, type: str):
        self.__type = type

    @property
    def model(self) -> str:
        return self.__model

    @model.setter
    def model(self, model: str):
        self.__model = model

    @property
    def amount(self) -> int:
        return self.__amount

    @amount.setter
    def amount(self, amount: int):
        self.__amount = amount
