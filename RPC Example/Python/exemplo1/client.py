import xmlrpc.client

proxy = xmlrpc.client.ServerProxy('http://localhost:8000/')
result = proxy.add(2, 3)
print(result)
