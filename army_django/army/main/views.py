from django.shortcuts import render
from main.for_usage.admin import Admin
from main.for_usage.soldier import Soldier
from main.for_usage.AdminInstance import AdminInstance
from django.http import HttpResponse, HttpResponseRedirect
from main.for_usage.military_technique import Technique
from main.for_usage.miliatary_base import Base
from main.for_usage.operations import Operation

def index(request):
    data = {
        'title': 'Главная страница',
        'values': ['Some', 'Hello', '123']
    }
    return render(request, 'main/index.html', data)

def about(request):
    return render(request, 'main/about.html')

def add(request):
    return render(request, 'main/add.html')

def delete(request):
    return render(request, 'main/delete.html')

def add_soldier(request):
    admin = Admin()

    if request.method == 'POST':
        try:
            first_name = request.POST.get('first_name')
            last_name = request.POST.get('last_name')
            age = request.POST.get('age')
            if int(age) < 0:
                return render(request, 'main/add_soldier.html', {'error_message': 'age must be greater than 0'})
            for_is_fit = request.POST.get('is_fit')
            if for_is_fit == 'healthy':
                is_fit = True
            else:
                is_fit = False
            admin.add_soldier(Soldier(first_name=first_name, last_name=last_name, age=age, is_fit=is_fit))
            return HttpResponseRedirect('/')
        except ValueError:
            return render(request, 'main/add_soldier.html', {'error_message': 'Unexpected error'})

    return render(request, 'main/add_soldier.html')

def add_technique(request):
    admin = Admin()

    if request.method == 'POST':
        try:
            type = request.POST.get('type')
            model = request.POST.get('model')
            amount = request.POST.get('amount')
            if int(amount) < 0:
                return render(request, 'main/add_technique.html', {'error_message': 'amount must be greater than 0'})
            admin.add_technique(Technique(type, model, amount))
            return HttpResponseRedirect('/')
        except ValueError:
            return render(request, 'main/add_technique.html', {'error_message': 'Unexpected error'})
    return render(request, 'main/add_technique.html')

def add_operation(request):
    admin = Admin()

    if request.method == 'POST':
        try:
            type = request.POST.get('type')
            direction = request.POST.get('model')
            admin.add_operation(Operation(type, direction))
            return HttpResponseRedirect('/')
        except ValueError:
            return render(request, 'main/add_operation.html', {'error_message': 'Unexpected error'})
    return render(request, 'main/add_operation.html')

def add_base(request):
    admin = Admin()

    if request.method == 'POST':
        try:
            place = request.POST.get('place')
            type = request.POST.get('type')
            amount_of_soldiers = request.POST.get('amount_of_soldiers')
            amount_of_technique = request.POST.get('amount_of_technique')
            admin.add_base(Base(place, type, amount_of_soldiers, amount_of_technique))
            return HttpResponseRedirect('/')
        except ValueError:
            return render(request, 'main/add_base.html', {'error_message': 'Unexpected error'})
    return render(request, 'main/add_base.html')

def delete_soldier(request):
    admin = Admin()

    if request.method == 'POST':
        try:
            first_name = request.POST.get('first_name')
            last_name = request.POST.get('last_name')
            admin.delete_soldier(first_name, last_name)
            return HttpResponseRedirect('/')
        except ValueError:
            return render(request, 'main/delete_soldier.html', {'error_message': 'Unexpected error'})

    return render(request, 'main/delete_soldier.html')

def delete_technique(request):
    admin = Admin()

    if request.method == 'POST':
        try:
            type = request.POST.get('type')
            model = request.POST.get('model')
            admin.delete_technique(type, model)
            return HttpResponseRedirect('/')
        except ValueError:
            return render(request, 'main/delete_technique.html', {'error_message': 'Unexpected error'})
    return render(request, 'main/delete_technique.html')

def delete_base(request):
    admin = Admin()

    if request.method == 'POST':
        try:
            place = request.POST.get('place')
            type = request.POST.get('type')
            admin.delete_base(place, type)
            return HttpResponseRedirect('/')
        except ValueError:
            return render(request, 'main/delete_base.html', {'error_message': 'Unexpected error'})
    return render(request, 'main/delete_base.html')

def delete_operation(request):
    admin = Admin()

    if request.method == 'POST':
        try:
            type = request.POST.get('type')
            direction = request.POST.get('model')
            admin.delete_operation(type, direction)
            return HttpResponseRedirect('/')
        except ValueError:
            return render(request, 'main/delete_operation.html', {'error_message': 'Unexpected error'})
    return render(request, 'main/delete_operation.html')

def show_all(request):
    global info
    info = []
    type = None
    admin = Admin()
    if request.method == 'POST':
        try:
            type = request.POST.get('type')
            info = (admin.get_all_from_table(type))
        except ValueError:
            return render(request, 'main/show_all.html', {'error_message': 'Unexpected error'})
    return render(request, 'main/show_all.html', {
        "type": type,
        "info": info
    })

