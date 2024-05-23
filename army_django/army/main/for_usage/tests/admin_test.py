import unittest

from admin import Admin

from miliatary_base import Base
from military_technique import Technique
from operations import Operation
from soldier import Soldier, Man


class TestAdmin(unittest.TestCase):
    def setUp(self):
        self.soldier = Soldier("m", "m", True, 20)
        self.base = Base("place", "type", 0, 0)
        self.operation = Operation("attack", "direction")
        self.technique = Technique("type", "model", 0)
        self.man = Man("f_name", "l_name", True, 18)

    def test_add_and_delete_soldier(self):
        Admin.add_operation(self.operation)
        Admin.add_base(self.base)
        Admin.add_soldier(self.soldier)
        self.assertEqual(self.soldier.first_name, Admin.get_first_name(self.soldier))
        self.assertEqual(self.soldier.last_name, Admin.get_last_name(self.soldier))
        Admin.delete_soldier(self.soldier.first_name, self.soldier.last_name)
        Admin.delete_base(self.base.place, self.base.type)
        Admin.delete_operation(self.operation.type, self.operation.direction)
        self.assertIsNone(Admin.get_first_name(self.soldier))
        self.assertIsNone(Admin.get_last_name(self.soldier))
        Admin.clear("soldiers")

    def test_add_and_delete_base(self):
        Admin.add_operation(self.operation)
        Admin.add_base(self.base)
        self.assertEqual(self.base.type, Admin.get_base_type(self.base))
        self.assertEqual(self.base.place, Admin.get_base_place(self.base))
        Admin.delete_base(self.base.place, self.base.type)
        Admin.delete_operation(self.operation.type, self.operation.direction)
        self.assertIsNone(Admin.get_base_type(self.base))
        self.assertIsNone(Admin.get_base_place(self.base))

    def test_add_and_delete_operation(self):
        Admin.add_operation(self.operation)
        self.assertEqual(self.operation.type, Admin.get_operation_type(self.operation))
        self.assertEqual(self.operation.direction, Admin.get_operation_direction(self.operation))
        Admin.delete_operation(self.operation.type, self.operation.direction)
        self.assertIsNone(Admin.get_operation_type(self.operation))
        self.assertIsNone(Admin.get_operation_direction(self.operation))

    def test_add_and_delete_technique(self):
        Admin.add_technique(self.technique)
        self.assertEqual(self.technique.type, Admin.get_technique_type(self.technique))
        self.assertEqual(self.technique.model, Admin.get_technique_model(self.technique))
        Admin.delete_technique(self.technique.type, self.technique.model)
        self.assertIsNone(Admin.get_technique_type(self.technique))
        self.assertIsNone(Admin.get_technique_model(self.technique))

    def test_mobilization(self):
        Admin.add_man(self.man)
        Admin.mobilization()
        self.assertEqual(self.man.first_name, Admin.get_all_soldiers()[-1][1])
        self.assertEqual(self.man.last_name, Admin.get_all_soldiers()[-1][2])
        self.assertEqual(self.man.is_fit, Admin.get_all_soldiers()[-1][3])
        self.assertEqual(self.man.age, Admin.get_all_soldiers()[-1][4])
        Admin.delete_man(self.man.first_name, self.man.last_name)
        Admin.clear("soldiers")

    def test_level_up(self):
        Admin.add_soldier(self.soldier)
        var1 = Admin.get_rank_from_soldier(self.soldier)
        Admin.level_up(self.soldier)
        var2 = Admin.get_rank_from_soldier(self.soldier)
        self.assertNotEqual(var1, var2)
        Admin.delete_soldier(self.soldier.first_name, self.soldier.last_name)
        Admin.clear("soldiers")

    def test_get_learned(self):
        Admin.add_soldier(self.soldier)
        self.assertFalse(Admin.get_learned_status(self.soldier))
        Admin.learning(Admin.get_soldier_id(self.soldier), "shooting")
        Admin.learning(Admin.get_soldier_id(self.soldier), "cardio")
        Admin.learning(Admin.get_soldier_id(self.soldier), "jumping_with_parachute")
        self.assertTrue(Admin.get_learned_status(self.soldier))
        Admin.delete_soldier(self.soldier.first_name, self.soldier.last_name)
        Admin.clear("soldiers")
