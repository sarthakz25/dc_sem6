import xmlrpc.client

server = xmlrpc.client.ServerProxy("http://localhost:1234")
# result = server.add(34, 35)

operations = {
    '1': server.add,
    '2': server.subtract,
    '3': server.multiply,
    '4': server.divide,
    '5': server.square,
    '6': server.cube
}

def user_interface():
    print("select an operation:")
    print("1. add")
    print("2. subtract")
    print("3. multiply")
    print("4. divide")
    print("5. square")
    print("6. cube")

    choice = input("enter a choice (1/2/3/4/5/6): ")

    if choice in ('5', '6'):
        x = int(input("enter a number: "))

        result = operations[choice](x)
        print("Result => ", result)

    elif choice in ('1', '2', '3', '4'):
        x = int(input("enter first number: "))
        y = int(input("enter second number: "))

        result = operations[choice](x, y)
        print("Result => ", result)

    else:
        print("invalid choice!")

while True:
    user_interface()

    exit_choice = input("do you want to perform another operation? (y/n)")
    if exit_choice.lower() != 'y':
        break

print("goodbye :)")
