import sys

def getString(str): 
	return str


def main(args):
	str = args
	str1 = getString(str)
	print "v: ", str1
	aa ="hope it's working"
	print "aaaaaaaaaaaaaaaaaaaaaaaaaaaa"
	print aa
	return str1

if __name__ == "__main__":
   main(sys.argv[1])