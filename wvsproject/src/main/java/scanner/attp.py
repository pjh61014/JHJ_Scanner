import csv

def getPatterns():
	dict = {}
	with open('pattern_dict.csv') as csvfile:
		reader = csv.DictReader(csvfile)
		for row in reader:
			#print (row['title'], row['pattern'])
			dict[row['title']]=row['pattern'].strip()
		csvfile.close()

	return dict



aa = getPatterns()
print len(aa)