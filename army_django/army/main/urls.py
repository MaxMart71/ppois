from django.urls import path
from . import views
urlpatterns = [
    path('', views.index, name='home'),
    path('about', views.about, name='about'),
    path('add_soldier/', views.add_soldier, name='add_soldier'),
    path('add_technique/', views.add_technique, name='add_technique'),
    path('add_base/', views.add_base, name='add_base'),
    path('add_operation/', views.add_operation, name='add_operation'),
    path('delete_operation/', views.delete_operation, name='delete_operation'),
    path('delete_soldier/', views.delete_soldier, name='delete_soldier'),
    path('delete_base/', views.delete_base, name='delete_base'),
    path('delete_technique/', views.delete_technique, name='delete_technique'),
    path('show_all/', views.show_all, name='show_all'),
    path('add/', views.add, name='add'),
    path('delete/', views.delete, name='delete')
]