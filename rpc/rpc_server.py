import xmlrpc.server

class MyServer:
    def add(self, a, b):
        return a + b
    
    def subtract(self, a, b):
        return a - b
    
    def multiply(self, a, b):
        return a * b
    
    def divide(self, a, b):
        return a / b
    
    def square(self, x):
        return x**2
    
    def cube(self, y):
        return y**3
    
server = xmlrpc.server.SimpleXMLRPCServer(("localhost", 1234))
server.register_instance(MyServer())
server.serve_forever()
