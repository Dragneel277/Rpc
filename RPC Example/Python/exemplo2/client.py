import xmlrpc.client

proxy = xmlrpc.client.ServerProxy('http://localhost:8000/')
result = proxy.square(4)
print(result) # Output: 16
