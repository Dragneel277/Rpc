import xmlrpc.server

def square(x):
    return x**2

server = xmlrpc.server.SimpleXMLRPCServer(('localhost', 8000))
server.register_function(square, 'square')
server.serve_forever()
