from math import pi,exp,sqrt

counts = [0 for i in range(26)]

print "The numeric value of A is:", ord('A')


# from: http://web2.uwindsor.ca/math/hlynka/zogheibhlynka.pdf
def phi(x):
    return 1.0 - 0.5*exp(-1.2*(x**1.3))

print [(x,phi(x)) for x in (0.5,1.0,2.0,3.0)]
print [(x,1-phi(x)) for x in (-0.5,-1.0,-2.0,-3.0)]
