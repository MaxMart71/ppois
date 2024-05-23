from main.for_usage.admin import Admin



def cli():
    choice = {
        "actions_with_soldiers": {
            "add": lambda soldier: Admin.add_soldier(soldier),
            "delete": lambda f_name, l_name: Admin.delete_soldier(f_name, l_name),
            "get_all_soldiers": Admin.get_all_soldiers(),
        },
        "actions_with_bases": {
            "add": lambda base: Admin.add_base(base),
            "delete": lambda place, type: Admin.delete_base(place, type),
            "get_all_bases": Admin.get_all_bases(),
        },
        "actions_with_operations": {
            "add": lambda operation: Admin.add_operation(operation),
            "delete": lambda type, direction: Admin.delete_operation(type, direction),
            "get_all_operations": Admin.get_all_operations(),
        },
        "actions_with_technique": {
            "add": lambda technique: Admin.add_technique(technique),
            "delete": lambda type, model: Admin.delete_technique(type, model),
            "get_all_techniques": Admin.get_all_technique(),
        },
        "actions_with_man": {
            "add": lambda man: Admin.add_man(man),
            "delete": lambda f_name, l_name: Admin.delete_man(f_name, l_name),
        },
        "mobilization": Admin.mobilization(),
        "learning": {
            "get_tasks": Admin.get_all_tasks(),
            "learning_process": lambda soldier_id, task_name: Admin.learning(soldier_id, task_name),
        }
    }
    print(f"Here are available actions\n{list(choice.keys())}")
    your_choice = input("What actions would you like to choose?:")
    assert your_choice in list(choice.keys()), "Invalid input"
    print("\n")
    options = f"Here are actions that you can do \n{list(choice[your_choice].keys())}"
    match your_choice:
        case "actions_with_soldiers":
            print(options)
            action = input("What action are you choosing?:")
            print("\n")
            match action:
                case "add":
                    x = input("input first_name, last_name, is_fit, age:").split()
                    choice[your_choice][action](Admin.make_soldier(x))
                case "delete":
                    first_name, last_name = input("input soldier you want to delete:").split()
                    choice[your_choice][action](first_name, last_name)
                case "get_all_soldiers":
                    print(choice[your_choice][action])
                case "back":
                    cli()
        case "actions_with_bases":
            print(options)
            action = input("What action are you choosing?:")
            print("\n")
            match action:
                case "add":
                    x = input("input place, type, amount_of_soldiers, amount_of_technique:").split()
                    choice[your_choice][action](Admin.make_base(x))
                case "delete":
                    place, type = input("input place and type of the base you want to delete:").split()
                    choice[your_choice][action](place, type)
                case "get_all_bases":
                    print(choice[your_choice][action])
                case "back":
                    cli()
        case "actions_with_operations":
            print(options)
            action = input("What action are you choosing?:")
            print("\n")
            match action:
                case "add":
                    x = input("input type, direction of the operation you want to add:").split()
                    choice[your_choice][action](Admin.make_operation(x))
                case "delete":
                    type, direction = input("input type and directions of the operation you want to delete:").split()
                    choice[your_choice][action](type, direction)
                case "get_all_operations":
                    print(choice[your_choice][action])
                case "back":
                    cli()
        case "actions_with_technique":
            print(options)
            action = input("What action are you choosing?:")
            print("\n")
            match action:
                case "add":
                    x = input("input type, model, amount of technique you want to add:").split()
                    choice[your_choice][action](Admin.make_technique(x))
                case "delete":
                    type, model = input("input type and model of the technique you want to delete:").split()
                    choice[your_choice][action](type, model)
                case "get_all_technique":
                    print(choice[your_choice][action])
                case "back":
                    cli()
        case "actions_with_man":
            print(options)
            action = input("What action are you choosing?:")
            print("\n")
            match action:
                case "add_man":
                    x = input("input first_name, last_name, is_fit, age:").split()
                    choice[your_choice][action](Admin.make_man(x))
                case "delete_man":
                    first_name, last_name = input("input soldier you want to delete:").split()
                    choice[your_choice][action](first_name, last_name)
                case "back":
                    cli()
        case "mobilization":
            choice[your_choice]()
        case "learning":
            print(options)
            action = input("What action are you choosing?:")
            print("\n")
            match action:
                case "get_tasks":
                    choice[your_choice]()
                case "learning_process":
                    f_name, l_name = input("input f_name and_l_name:").split()
                    task_name = input(f"input task_name from available: {Admin.get_all_tasks()}")
                    choice[your_choice](Admin.get_soldier_id_by_f_name_and_l_name(f_name, l_name), task_name)
                case "back":
                    cli()

