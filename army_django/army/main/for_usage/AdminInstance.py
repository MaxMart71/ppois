from main.for_usage.admin import Admin

class AdminInstance():
    _instance: Admin = None

    @staticmethod
    def get_instance():
        if AdminInstance._instance is None:
            _instance = Admin()
        return AdminInstance._instance