
class Operation:

    def __init__(self, type: str, direction: str):
        types = ["attack", "defend"]
        assert type in types, "type must be one of {}".format(types)
        self.__type = type
        self.__direction = direction

    @property
    def type(self) -> str:
        return self.__type

    @type.setter
    def type(self, type: str):
        self.__type = type

    @property
    def direction(self) -> str:
        return self.__direction

    @direction.setter
    def direction(self, direction: str):
        self.__direction = direction
