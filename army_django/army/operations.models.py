# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Base(models.Model):
    place = models.TextField()
    type = models.TextField()
    amount_of_soldiers = models.IntegerField(blank=True, null=True)
    amount_of_technique = models.IntegerField(blank=True, null=True)
    operation = models.ForeignKey('Operations', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'base'


class LearningProcess(models.Model):
    soldier = models.ForeignKey('Soldiers', models.DO_NOTHING, blank=True, null=True)
    task = models.ForeignKey('Tasks', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'learning_process'
        unique_together = (('soldier', 'task'),)


class Mans(models.Model):
    first_name = models.TextField()
    last_name = models.TextField()
    is_fit = models.BooleanField(blank=True, null=True)
    age = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'mans'


class Operations(models.Model):
    type = models.TextField(blank=True, null=True)
    direction = models.TextField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'operations'


class Soldiers(models.Model):
    first_name = models.TextField()
    last_name = models.TextField()
    is_fit = models.BooleanField(blank=True, null=True)
    age = models.IntegerField(blank=True, null=True)
    base = models.ForeignKey(Base, models.DO_NOTHING, blank=True, null=True)
    rank = models.TextField(blank=True, null=True)
    is_learned = models.BooleanField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'soldiers'


class Tasks(models.Model):
    task_name = models.TextField(blank=True, null=True)
    description = models.TextField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tasks'


class Technique(models.Model):
    type = models.TextField()
    model = models.TextField()
    amount = models.IntegerField(blank=True, null=True)
    base = models.ForeignKey(Base, models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'technique'
