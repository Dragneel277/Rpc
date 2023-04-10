import xmlrpc.server

def add_numbers(x, y):
    return x + y

server = xmlrpc.server.SimpleXMLRPCServer(('localhost', 8000))
server.register_function(add_numbers, 'add')
server.serve_forever()
