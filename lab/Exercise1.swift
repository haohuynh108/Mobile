import Foundation

func nonNegative(x:Int) -> Int  {
    if x < 0 { 
        return x*3
    }else{
        return x*5
    }
}

print(nonNegative(x:4))
print(nonNegative(x:-3))


func range1030(x: Int, y: Int) -> Bool {
    if x >= 10 && x <= 30
    {
        return true
    } 
    else if y >= 10 && y <= 30
    {
        return true
    }
    else 
    {
        return false
    }
}

print(range1030(x:20, y:25))
print(range1030(x:10, y:30))
print(range1030(x:0, y:33))
