import csv
import random

from main.for_usage.enums.ranks import Rank
from main.for_usage.military_technique import Technique
from main.for_usage.miliatary_base import Base
from main.for_usage.operations import Operation
from main.for_usage.soldier import Soldier, Man
from main.for_usage.util.database_manager import *
import main.for_usage.enums


class Admin:

    @staticmethod
    def add_soldier(soldier: Soldier):
        conn = None
        try:
            conn = DbManager.connection()
            base_id = random.choice(Admin.get_all_bases_id())
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                  INSERT INTO soldiers (first_name, last_name, is_fit, age, base_id, rank, is_learned)
                  VALUES ('{soldier.first_name}', '{soldier.last_name}', {soldier.is_fit},
                  {soldier.age}, {base_id}, '{soldier.rank.value}', {soldier.is_learned})
                  """
                )
            conn.commit()
            Admin.upd_amount_of_soldiers(base_id)
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def delete_soldier(first_name: str, last_name: str):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                   DELETE FROM soldiers
                   WHERE first_name = '{first_name}'
                   AND last_name = '{last_name}'"""
                )
            conn.commit()
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            conn.close()

    @staticmethod
    def add_technique(technique: Technique):
        conn = None
        try:
            conn = DbManager.connection()
            base_id = random.choice(Admin.get_all_bases_id())
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                     INSERT INTO technique (type, model, amount, base_id)
                     VALUES ('{technique.type}', '{technique.model}', {technique.amount}, {base_id})
                     """
                )
            conn.commit()
            Admin.upd_amount_of_technique(base_id)
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def delete_technique(type: str, model: str):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                      DELETE FROM technique
                      WHERE type = '{type}'
                      AND model = '{model}'
                      """
                )
            conn.commit()
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            conn.close()

    @staticmethod
    def add_base(base: Base):
        conn = None
        try:
            conn = DbManager.connection()
            operation_id = random.choice(Admin.get_all_operations_id())
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                         INSERT INTO base (place, type, amount_of_soldiers, amount_of_technique, operation_id)
                         VALUES ('{base.place}', '{base.type}', 
                         {base.amount_of_soldiers}, {base.amount_of_technique}, {operation_id})
                         """
                )
            conn.commit()
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def delete_base(place: str, type: str):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                          DELETE FROM base WHERE place = '{place}' AND type = '{type}'"""
                )
            conn.commit()
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            conn.close()

    @staticmethod
    def get_all_bases_id() -> list:
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    "SELECT id FROM base"
                )
                ids = [item[0] for item in cursor.fetchall()]
                return ids
        except psycopg2.Error as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def upd_amount_of_soldiers(base_id: int):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""UPDATE base SET amount_of_soldiers = amount_of_soldiers + 1
                    WHERE id = {base_id}"""
                )
                conn.commit()
        except psycopg2.Error as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def upd_amount_of_technique(base_id: int):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""UPDATE base SET amount_of_technique = (amount_of_technique + 1)
                    WHERE id = {base_id}"""
                )
                conn.commit()
        except psycopg2.Error as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def add_operation(operation: Operation):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                             INSERT INTO operations (type, direction)
                             VALUES ('{operation.type}', '{operation.direction}')
                             """
                )
            conn.commit()
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def delete_operation(type: str, direction: str):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                             DELETE FROM operations WHERE type = '{type}' AND direction = '{direction}'"""
                )
            conn.commit()
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            conn.close()

    @staticmethod
    def get_rank_from_soldier(soldier: Soldier):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                             SELECT rank FROM soldiers WHERE id = {Admin.get_soldier_id(soldier)}"""
                )
                return cursor.fetchone()[0]
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            conn.close()

    @staticmethod
    def level_up(soldier: Soldier):
        enum_list = [i.value for i in list(Rank)]
        if soldier.rank != enum_list[-1]:
            soldier.rank = enum_list[enum_list.index(Admin.get_rank_from_soldier(soldier)) + 1]
        else:
            print("[INFO] It is the highest rank")
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"UPDATE soldiers SET rank = '{soldier.rank}'"
                    f"WHERE first_name = '{soldier.first_name}' AND last_name = '{soldier.last_name}'"
                )
                conn.commit()
        except psycopg2.Error as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_all_operations_id() -> list:
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    "SELECT id FROM operations"
                )
                ids = [item[0] for item in cursor.fetchall()]
                return ids
        except psycopg2.Error as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def add_man(man: Man):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                      INSERT INTO mans (first_name, last_name, is_fit, age)
                      VALUES ('{man.first_name}', '{man.last_name}', {man.is_fit}, {man.age})
                      """
                )
            conn.commit()
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def delete_man(first_name: str, last_name: str):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                       DELETE FROM mans WHERE first_name = '{first_name}' AND last_name = '{last_name}'"""
                )
            conn.commit()
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def mobilization():
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    "SELECT * FROM mans WHERE age >= 18  AND is_fit = True"
                )
                for item in cursor.fetchall():
                    Admin.add_soldier(Admin.make_soldier_for_mobilization(item))
            conn.commit()
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            conn.close()

    @staticmethod
    def make_soldier_for_mobilization(t: list | tuple) -> Soldier:
        soldier = Soldier(t[1], t[2], t[3], t[4])
        return soldier

    @staticmethod
    def make_soldier(t: list | tuple) -> Soldier:
        soldier = Soldier(t[0], t[1], t[2], t[3])
        return soldier

    @staticmethod
    def make_man(t: list | tuple) -> Man:
        man = Man(t[0], t[1], t[2], t[3])
        return man

    @staticmethod
    def make_base(t: list | tuple) -> Base:
        base = Base(t[0], t[1], t[2], t[3])
        return base

    @staticmethod
    def make_technique(t: list | tuple) -> Technique:
        technique = Technique(t[0], t[1], t[2])
        return technique

    @staticmethod
    def make_operation(t: list | tuple) -> Operation:
        operation = Operation(t[0], t[1])
        return operation

    @staticmethod
    def get_learned(soldier_id: int):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                             UPDATE soldiers SET is_learned = True WHERE id = {soldier_id} 
                             """
                )
            conn.commit()
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def is_learned(soldier_id: int) -> bool:
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                         SELECT task_id FROM learning_process WHERE soldier_id = {soldier_id} 
                     """
                )
                return len(cursor.fetchall()) == len(Admin.get_all_tasks())
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def learning(soldier_id, task_name: str):
        conn = None
        assert task_name in Admin.get_all_tasks(), "There is no such task"
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                       INSERT INTO learning_process(soldier_id, task_id)
                       VALUES({soldier_id}, {Admin.get_task_id(task_name)})
                    """
                )
            conn.commit()
            if Admin.is_learned(soldier_id):
                Admin.get_learned(soldier_id)
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_soldier_id_by_f_name_and_l_name(f_name: str, l_name: str):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                        SELECT id FROM soldiers WHERE first_name = '{f_name}'
                        AND last_name = '{l_name}'
                    """
                )
                soldier_id = cursor.fetchone()[0]
                return soldier_id
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_all_tasks():
        conn = None
        tasks = []
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                        SELECT task_name FROM tasks
                    """
                )
                for row in cursor.fetchall():
                    tasks.append(str(row[0]))
                return tasks
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_task_id(task_name: str):
        conn = None
        assert task_name in Admin.get_all_tasks(), f"There is not such task, available tasks: {Admin.get_all_tasks()}"
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                               SELECT id FROM tasks WHERE task_name = '{task_name}' 
                                """
                )
                return cursor.fetchone()[0]
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn is not None:
                conn.close()

    @staticmethod
    def get_all_soldiers():
        return Admin.get_all_from_table("soldiers")

    @staticmethod
    def get_all_technique():
        return Admin.get_all_from_table("technique")

    @staticmethod
    def get_all_bases():
        return Admin.get_all_from_table("base")

    @staticmethod
    def get_all_operations():
        return Admin.get_all_from_table("operations")

    @staticmethod
    def get_all_from_table(table_name: str):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(f""" SELECT * FROM {table_name} """)
                return cursor.fetchall()
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_all_bases_by_operation(operation: Operation):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(f""" SELECT id FROM base WHERE operation_id = {Admin.get_operation_id(operation)} """)
                return [item[0] for item in cursor.fetchall()]
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def clear(table_name: str):
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(f"""TRUNCATE {table_name} CASCADE""")
            conn.commit()
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_id(table_name, param1, param2):
        for_query = {
            "soldiers": ("first_name", "last_name"),
            "base": ("place", "type"),
            "operations": ("type", "direction"),
            "technique": ("type", "model"),
            "mans": ("first_name", "last_name")
        }
        assert table_name in list(for_query.keys()), "Invalid table name"
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(
                    f"""
                        SELECT id FROM {table_name} WHERE {for_query[table_name][0]} = '{param1}'
                        AND {for_query[table_name][1]} = '{param2}'
                    """
                )
                try:
                    soldier_id = cursor.fetchone()[0]
                except TypeError:
                    soldier_id = None
                finally:
                    return soldier_id
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_soldier_id(soldier: Soldier):
        return Admin.get_id("soldiers", soldier.first_name, soldier.last_name)

    @staticmethod
    def get_base_id(base: Base):
        return Admin.get_id("base", base.place, base.type)

    @staticmethod
    def get_operation_id(operation: Operation):
        return Admin.get_id("operations", operation.type, operation.direction)

    @staticmethod
    def get_man_id(man: Man):
        return Admin.get_id("mans", man.first_name, man.last_name)

    @staticmethod
    def get_technique_id(technique: Technique):
        return Admin.get_id("technique", technique.type, technique.model)

    # for tests
    @staticmethod
    def get_name(soldier: Soldier, name: str):
        conn = None
        if Admin.get_soldier_id(soldier) is None:
            return None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(f""" SELECT {name} FROM soldiers WHERE id = {Admin.get_soldier_id(soldier)} """)
                return cursor.fetchone()[0]
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_last_name(soldier: Soldier):
        return Admin.get_name(soldier, "last_name")

    @staticmethod
    def get_first_name(soldier: Soldier):
        return Admin.get_name(soldier, "first_name")

    @staticmethod
    def get_base_type(base: Base):
        if Admin.get_base_id(base) is None:
            return None
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(f""" SELECT type FROM base WHERE id = {Admin.get_base_id(base)} """)
                return cursor.fetchone()[0]
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_operation_type(operation: Operation):
        if Admin.get_operation_id(operation) is None:
            return None
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(f""" SELECT type FROM operations WHERE id = {Admin.get_operation_id(operation)} """)
                return cursor.fetchone()[0]
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_technique_type(technique: Technique):
        if Admin.get_technique_id(technique) is None:
            return None
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(f""" SELECT type FROM technique WHERE id = {Admin.get_technique_id(technique)} """)
                return cursor.fetchone()[0]
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_base_place(base: Base):
        conn = None
        if Admin.get_base_id(base) is None:
            return None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(f""" SELECT place FROM base WHERE id = {Admin.get_base_id(base)} """)
                return cursor.fetchone()[0]
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_operation_direction(operation: Operation):
        if Admin.get_operation_id(operation) is None:
            return None
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(f""" SELECT direction FROM operations WHERE id = {Admin.get_operation_id(operation)} """)
                return cursor.fetchone()[0]
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_technique_model(technique: Technique):
        if Admin.get_technique_id(technique) is None:
            return None
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(f""" SELECT model FROM technique WHERE id = {Admin.get_technique_id(technique)} """)
                return cursor.fetchone()[0]
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()

    @staticmethod
    def get_learned_status(soldier: Soldier):
        if Admin.get_soldier_id(soldier) is None:
            return None
        conn = None
        try:
            conn = DbManager.connection()
            with conn.cursor() as cursor:
                cursor.execute(f""" SELECT is_learned FROM soldiers WHERE id = {Admin.get_soldier_id(soldier)} """)
                return cursor.fetchone()[0]
        except psycopg2.DatabaseError as ex:
            print("[INFO] Database Error : ", ex)
        finally:
            if conn:
                conn.close()
